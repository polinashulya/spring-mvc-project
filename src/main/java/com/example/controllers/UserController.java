package com.example.controllers;

import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.exception.ServletCustomException;
import com.example.service.dto.UserDto;
import com.example.service.impl.CountryServiceImpl;
import com.example.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final CountryServiceImpl countryService;

    @GetMapping
    public String findAllUsers(Model model,
                               @RequestParam(name = "sortBy", required = false) String sortBy,
                               @RequestParam(name = "sortType", required = false) String sortType,
                               @RequestParam(name = "countryId", required = false) String countryId,
                               @RequestParam(name = "searchText", required = false) String search,
                               @RequestParam(name = "page", required = false) String page,
                               @RequestParam(name = "pageSize", required = false) String pageSize) {

        List<UserEntity> users = userService.getAll(sortBy, sortType, countryId, search, page, pageSize);

        int totalUsers = userService.getTotalResult(sortBy, sortType, countryId, search);

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("users", users);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortType", sortType);
        model.addAttribute("currentCountryId", countryId);

        setCountriesToModel(model);

        return "users";
    }

    private void setCountriesToModel(Model model) {
        List<CountryEntity> countries = this.countryService.findAll();
        model.addAttribute("countries", countries);
    }

    @GetMapping("/adding_form")
    public String addingForm(Model model) {

        setCountriesToModel(model);

        return "add_user";
    }

    @PostMapping
    public String save(Model model, UserDto userDto) {
//@RequestParam(name = "login", required = false) String login,
//                       @RequestParam(name = "password", required = false) String password,
//                       @RequestParam(name = "name", required = false) String name,
//                       @RequestParam(name = "surname", required = false) String surname,
//                       @RequestParam(name = "countryId", required = false) String countryId,
//                       @RequestParam(name = "birthDate", required = false) String birthDate
        LocalDate parsedBirthDate = LocalDate.parse(userDto.getBirthDate());
        UserEntity user = UserEntity.builder()
                .login(userDto.getLogin().trim())
                .password(userDto.getPassword())
                .name(userDto.getName().trim())
                .surname(userDto.getSurname().trim())
                .country(
                        CountryEntity.builder()
                                .id(Long.valueOf(userDto.getCountryId()))
                                .build()
                )
                .birthDate(parsedBirthDate)
                .build();


        userService.add(user);

        model.addAttribute("user", user);

        return "redirect:/users";

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Model model,
                                       @PathVariable(name = "id") String userId) {

        try {
            userService.deleteById(Long.valueOf(userId));
        } catch (Exception e) {
            logger.error("Error while executing DeleteUserCommand", e);
            throw new ServletCustomException("Error while executing DeleteUserCommand", e);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
