package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.exception.ServletCustomException;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
    private static final String USERS_ACTION_URL = "mainServlet?action=users";
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public DeleteUserCommand(final HttpServletRequest request,
                             final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }

    @Override
    public String execute() throws CommandException {

        try {
            Long userId = Long.valueOf(request.getParameter("userId"));
            userService.deleteById(userId);

        } catch (Exception e) {
            logger.error("Error while executing DeleteUserCommand", e);
            throw new ServletCustomException("Error while executing DeleteUserCommand", e);
        }
        return USERS_ACTION_URL;
    }
}
