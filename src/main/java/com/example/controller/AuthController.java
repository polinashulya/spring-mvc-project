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

@Controller
@AllArgsConstructor
public class AuthController {

    private final SecurityService securityService;
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


//    @GetMapping("/default")
//    public String defaultAfterLogin() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//
//        if (roles.contains("ROLE_ADMIN")) {
//            return "redirect:/admin/home"; // Admin home page URI
//        } else if (roles.contains("ROLE_EMPLOYEE")) {
//            return "redirect:/employee/home"; // Employee home page URI
//        } else if (roles.contains("ROLE_CLIENT")) {
//            return "redirect:/client/home"; // Client home page URI
//        } else {
//            return "redirect:/login"; // Default page
//        }
//    }


//    private final UserSecurityService userSecurityService;
//    private final SecurityService securityService;
//    private final RoleServiceSearch roleService;
//    private final UserService userService;
//
//    @GetMapping({"/"})
//    public String welcome(Principal principal) {
//        if (Objects.isNull(principal) || principal.getName().isEmpty()) {
//            return ControllerHelper.redirectTo(ControllerPageHolder.URL_USER_LOGIN_PAGE);
//        } else {
//            UserDto userDTO = userService.findByEmail(principal.getName());
//            Set<UserRoleDto> roles = userDTO.getRoles();
//            if (isRolePresent(roles, UserRoles.CLIENT)) {
//                return ControllerHelper.redirectTo(ControllerPageHolder.URL_CLIENT_PERSONAL_CABINET_PAGE);
//            } else if (isRolePresent(roles, UserRoles.EMPLOYEE)) {
//                return ControllerHelper.redirectTo(ControllerPageHolder.URL_USER_EMPLOYEE_PERSONAL_CABINET_PAGE);
//            } else if (isRolePresent(roles, UserRoles.ADMIN)) {
//                return ControllerHelper.redirectTo("admin/users");
//            } else {
//                return ControllerHelper.redirectTo(ControllerPageHolder.URL_USER_LOGIN_PAGE);
//            }
//        }
//    }

//    private boolean isRolePresent(Set<UserRoleDto> roles, UserRoles userRole) {
//        return roles.stream().anyMatch(role -> role.getName().equals(userRole.name()));
//    }
//
//    @GetMapping("login")
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
//                              @RequestParam(value = "logout", required = false) String logout,
//                              @RequestParam(value = "accessDenied", required = false) String accessDenied) {
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (Objects.nonNull(error)) {
//            modelAndView.addObject("error", "Имя пользователя или пароль неверны.");
//        }
//        if (Objects.nonNull(logout)) {
//            modelAndView.addObject("message", "Успешны выход.");
//        }
//        if (Objects.nonNull(accessDenied)) {
//            modelAndView.addObject("message", "У вас нет прав.");
//        }
//        modelAndView.setViewName("user/login");
//
//        return modelAndView;
//    }
//
//    private void addUserRolesToModel(Model model) {
//        model.addAttribute("userRoles", roleService.findAll());
//    }
//
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
 //       securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "common/successfulRegistration";
    }

    private boolean isPasswordsMatching(UserSignUpDTO userForm) {
        return userForm.getPassword().equals(userForm.getMatchingPassword());
    }


//    @RequestMapping(value = ControllerPageHolder.URL_CONFIRM_ACCOUNT,
//            method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView confirmUserAccount(ModelAndView modelAndView,
//                                           @RequestParam("token") String confirmationToken,
//                                           HttpServletRequest request) {
//        try {
//            userSecurityService.confirmUserAccount(confirmationToken);
//            modelAndView.setViewName(ControllerPageHolder.ACCOUNT_VERIFIED_PAGE);
//        } catch (ConfirmationTokeBrokenLinkException ex) {
//            modelAndView.addObject("message", ex.getLocalizedMessage());
//            modelAndView.setViewName("error");
//        }
//
//        return modelAndView;
//    }
}


