package com.example.servlet.factory.impl;

import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.CommandException;
import com.example.exception.ServletCustomException;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AddUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);
    private static final String USERS_ACTION_URL = "mainServlet?action=users";
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public AddUserCommand(final HttpServletRequest request,
                          final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }

    @Override
    public String execute() throws CommandException {

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String firstname = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String countryId = request.getParameter("countryId");
            String birthDate = request.getParameter("birthDate");

            if (countryId == null) {
                throw new ServletCustomException("Country ID is required.");
            }

            User user = User.builder()
                    .login(login.trim())
                    .password(password)
                    .firstname(firstname.trim())
                    .surname(surname.trim())
                    .country(
                            Country.builder()
                                    .id(Long.valueOf(countryId))
                                    .build()
                    )
                    .birthDate(LocalDate.parse(birthDate))
                    .banned(false)
                    .deleted(false)
                    .build();


            userService.add(user);

            request.setAttribute("user", user);

        } catch (Exception e) {
            logger.error("Error while executing AddUserCommand", e);
            throw new ServletCustomException("Error while executing AddUserCommand", e);
        }
        return USERS_ACTION_URL;
    }
}
