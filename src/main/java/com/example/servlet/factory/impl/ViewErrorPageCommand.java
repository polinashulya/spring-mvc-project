package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewErrorPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ViewErrorPageCommand.class);

    private static final String ERROR_PAGE_JSP_PATH = "/error_page.jsp";
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public ViewErrorPageCommand(final HttpServletRequest request,
                                final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }


    @Override
    public String execute() throws CommandException {
        try {
            return ERROR_PAGE_JSP_PATH;
        } catch (Exception e) {
            logger.error("Error while executing ViewErrorPageCommand", e);
            throw new CommandException("Error while executing ViewErrorPageCommand", e);
        }
    }

}
