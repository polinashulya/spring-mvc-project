package com.example.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {

        System.err.println(e.getCause());

        model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());

        return "error_page";
    }
}