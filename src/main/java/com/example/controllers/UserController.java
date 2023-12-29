package com.example.controllers;

import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.exception.ServletCustomException;
import com.example.service.dto.UserDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
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
    public String findAllUsers(Model model, @ModelAttribute UserSearchCriteriaDto userSearchCriteriaDto) {

        List<UserEntity> users = userService.getAll(userSearchCriteriaDto.getSortBy(), userSearchCriteriaDto.getSortType(),
                userSearchCriteriaDto.getCountryId(), userSearchCriteriaDto.getSearch(),
                userSearchCriteriaDto.getPage(), userSearchCriteriaDto.getPageSize());

        int totalUsers = userService.getTotalResult(userSearchCriteriaDto.getSortBy(), userSearchCriteriaDto.getSortType(),
                userSearchCriteriaDto.getCountryId(), userSearchCriteriaDto.getSearch());

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("users", users);
        model.addAttribute("sortBy", userSearchCriteriaDto.getSortBy());
        model.addAttribute("sortType", userSearchCriteriaDto.getSortType());
        model.addAttribute("currentCountryId", userSearchCriteriaDto.getCountryId());

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
    public String save(Model model, @ModelAttribute UserDto userDto) {

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
