package com.example.servlet.factory;

import com.example.exception.CommandException;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CommandFactory {

    Command createCommand(String actionName,
                          HttpServletRequest request,
                          HttpServletResponse response)
            throws CommandException;

    boolean isValidAction(String action);
}
