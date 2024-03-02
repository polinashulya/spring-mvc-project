package com.example.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;

final class ControllerHelper {


    private ControllerHelper() {

    }

    static boolean checkBindingResult(BindingResult bindingResult) {
        return bindingResult.hasErrors();
    }

    static String redirectTo(String redirectTo) {
        if (redirectTo.startsWith("/")) {
            redirectTo = redirectTo.substring(1);
        }
        return "redirect:/" + redirectTo;
    }

    static String goBackTo(String goBackTo) {
        return goBackTo;
    }


    static boolean hasRole(String... roles) {
        boolean result = false;
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            String userRole = authority.getAuthority();
            for (String role : roles) {
                if (role.equals(userRole)) {
                    result = true;
                    break;
                }
            }

            if (result) {
                break;
            }
        }

        return result;
    }
}
