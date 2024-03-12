package com.example.controller;

import com.example.entity.UserRoles;
import com.example.exception.ControllerCustomException;
import com.example.exception.UserAlreadyExistException;
import com.example.service.SecurityService;
import com.example.service.UserService;
import com.example.service.dto.ClientDto;
import com.example.service.dto.UserDto;
import com.example.service.dto.UserRoleDto;
import com.example.service.dto.UserSignUpDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class AuthController {

    private final SecurityService securityService;
    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "accessDenied", required = false) String accessDenied) {

        ModelAndView modelAndView = new ModelAndView();

        if (Objects.nonNull(error)) {
            modelAndView.addObject("error", "Username or password is incorrect.");
        }
        if (Objects.nonNull(logout)) {
            modelAndView.addObject("message", "Successful exit.");
        }
        if (Objects.nonNull(accessDenied)) {
            modelAndView.addObject("message", "You have no access.");
        }
        modelAndView.setViewName("user/login");

        return modelAndView;
    }

//    private void addUserRolesToModel(Model model) {
//        model.addAttribute("userRoles", roleService.findAll());
//    }

    @GetMapping("/user/sign-up")
    public String showRegistrationForm(Model model) {
        try {

            UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
            model.addAttribute("userSignUp", userSignUpDTO);

            return "user/userSignUp";
        } catch (Exception e) {
            throw new ControllerCustomException("Error while executing adding form", e);
        }
    }
//
    @PostMapping("/user/sign-up")
    public String signUpUser(Model model,
                             @Validated @ModelAttribute
                                     ("userForm") UserSignUpDTO userForm,
                             BindingResult bindingResult) {

//        if (ControllerHelper.checkBindingResult(bindingResult)) {
//            return ControllerPageHolder.USER_SIGN_UP_PAGE;
//        }
//
//        if (!isPasswordsMatching(userForm)) {
//            model.addAttribute("error", "Пароли не совпали!");
//            return ControllerPageHolder.USER_SIGN_UP_PAGE;
//        }
//
        try {
            String email = userService.signUpUser(userForm);
            model.addAttribute("email", email);
        } catch (UserAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
            return "user/userSignUp";
        }
//
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "common/successfulRegistration";
    }


}


