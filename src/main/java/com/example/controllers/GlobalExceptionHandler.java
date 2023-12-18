package com.example.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {

        e.printStackTrace();

        model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());

        return "error_page";
    }
}