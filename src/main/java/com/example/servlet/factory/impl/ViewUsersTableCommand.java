package com.example.servlet.factory.impl;

import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.CommandException;
import com.example.exception.ServiceException;
import com.example.service.CountryService;
import com.example.service.UserService;
import com.example.service.impl.CountryServiceImpl;
import com.example.service.impl.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ViewUsersTableCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ViewUsersTableCommand.class);
    private static final String USERS_JSP_PATH = "/WEB-INF/users.jsp";
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;
    private final CountryService countryService;

    public ViewUsersTableCommand(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
        countryService = new CountryServiceImpl();
    }


    @Override
    public String execute() throws CommandException {
        try {
            String sortBy = request.getParameter("sortBy");
            String sortType = request.getParameter("sortType");
            String countryId = request.getParameter("countryId");
            String search = request.getParameter("searchText");
            int totalUsers = 0;

            String page = request.getParameter("page");
            String pageSize = request.getParameter("pageSize");

            List<User> users = userService.getAll(sortBy, sortType, countryId, search, page, pageSize);

            totalUsers = userService.getTotalResult(sortBy, sortType, countryId, search);

            request.setAttribute("totalResult", totalUsers);
            request.setAttribute("users", users);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("sortType", sortType);
            request.setAttribute("currentCountryId", countryId);

            List<Country> countries = this.countryService.findAll();
            request.setAttribute("countries", countries);

            return USERS_JSP_PATH;

        } catch (Exception e) {
            logger.error("Error while executing ViewUsersTableCommand", e);
            throw new CommandException("Error while showing a new user", e);
        }

    }
}
