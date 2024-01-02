package com.example.controllers;

import com.example.exception.ControllerCustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/")
    public String mainPage() {

        try {
            return "index";
        } catch (Exception e) {
            logger.error("Error while executing DeleteUserCommand", e);
            throw new ControllerCustomException("Error while executing DeleteUserCommand", e);
        }
    }


}
