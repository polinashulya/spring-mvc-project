//package com.example.controller;
//
//import com.example.exception.ControllerCustomException;
//import com.example.service.dto.CountryDto;
//import com.example.service.dto.UserDto;
//import com.example.service.dto.search.UserSearchCriteriaDto;
//import com.example.service.impl.CountryServiceImpl;
//import com.example.service.impl.UserServiceImpl;
//import lombok.AllArgsConstructor;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/users")
//@AllArgsConstructor
//public class UserController {
//
//    private static final Logger logger = LogManager.getLogger(UserController.class);
//
//    private final UserServiceImpl userService;
//    private final CountryServiceImpl countryService;
//
//    @GetMapping
//    public String findAllUsers(Model model, @ModelAttribute UserSearchCriteriaDto userSearchCriteriaDto) {
//        try {
//            List<UserDto> users = userService.getAll(userSearchCriteriaDto.getSortBy(), userSearchCriteriaDto.getSortType(),
//                    userSearchCriteriaDto.getCountryId(), userSearchCriteriaDto.getSearch(),
//                    userSearchCriteriaDto.getPage(), userSearchCriteriaDto.getPageSize());
//
//            int totalUsers = userService.getTotalResult(userSearchCriteriaDto.getSortBy(), userSearchCriteriaDto.getSortType(),
//                    userSearchCriteriaDto.getCountryId(), userSearchCriteriaDto.getSearch());
//
//            model.addAttribute("totalUsers", totalUsers);
//            model.addAttribute("users", users);
//            model.addAttribute("sortBy", userSearchCriteriaDto.getSortBy());
//            model.addAttribute("sortType", userSearchCriteriaDto.getSortType());
//            model.addAttribute("currentCountryId", userSearchCriteriaDto.getCountryId());
//
//            setCountriesToModel(model);
//
//            return "users";
//        } catch (Exception e) {
//            logger.error("Error while executing find all users", e);
//            throw new ControllerCustomException("Error while executing find all users", e);
//        }
//    }
//
//    private void setCountriesToModel(Model model) {
//        List<CountryDto> countries = this.countryService.findAll();
//        model.addAttribute("countries", countries);
//    }
//
//    @GetMapping("/adding_form")
//    public String addingForm(Model model) {
//        try {
//            setCountriesToModel(model);
//            return "add_user";
//        } catch (Exception e) {
//            logger.error("Error while executing adding form", e);
//            throw new ControllerCustomException("Error while executing adding form", e);
//        }
//    }
//
//    @PostMapping
//    public String save(@ModelAttribute UserDto userDto) {
//        try {
//            userService.add(userDto);
//            return "redirect:/users";
//        } catch (Exception e) {
//            logger.error("Error while executing saving", e);
//            throw new ControllerCustomException("Error while executing saving", e);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable(name = "id") String userId) {
//        try {
//            userService.deleteById(Long.valueOf(userId));
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            logger.error("Error while executing deleting", e);
//            throw new ControllerCustomException("Error while executing deleting", e);
//        }
//    }
//
//}
