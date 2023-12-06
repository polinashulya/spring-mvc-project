package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.servlet.AllowedActions;
import com.example.servlet.Command;
import com.example.servlet.factory.CommandFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactoryImpl implements CommandFactory {

    private static final Logger logger = LogManager.getLogger(CommandFactoryImpl.class);

    @Getter
    private static final CommandFactoryImpl instance = new CommandFactoryImpl();

    public CommandFactoryImpl() {

    }

    @Override
    public Command createCommand(final String action,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)
            throws CommandException {

        switch (action) {
            case "users" -> {
                return new ViewUsersTableCommand(request, response);
            }
            case "adding_form" -> {
                return new ViewAddUserPageCommand(request, response);
            }
            case "add_user" -> {
                return new AddUserCommand(request, response);
            }
            case "delete_user" -> {
                return new DeleteUserCommand(request, response);
            }
            case "main_page" -> {
                return new ViewMainPageCommand(request, response);
            }
            case "error_page" -> {
                return new ViewErrorPageCommand(request, response);
            }
        }

        logger.error("No command with name {}", action);
        throw new CommandException("No command with name " + action);
    }

    @Override
    public boolean isValidAction(String action) {
        try {
            AllowedActions.valueOf(action.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("Invalid action: ", action);
            return false;
        }
    }

}
