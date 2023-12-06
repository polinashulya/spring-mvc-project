package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewMainPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ViewMainPageCommand.class);

    private static final String INDEX_JSP_PATH = "/index.jsp";
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public ViewMainPageCommand(final HttpServletRequest request,
                               final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }


    @Override
    public String execute() throws CommandException {
        try {
            return INDEX_JSP_PATH;
        } catch (Exception e) {
            logger.error("Error while executing ViewMainPageCommand", e);
            throw new CommandException("Error while executing ViewMainPageCommand", e);
        }
    }

}
