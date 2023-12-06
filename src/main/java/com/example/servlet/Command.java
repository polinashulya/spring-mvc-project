package com.example.servlet;

import com.example.exception.CommandException;

public interface Command {

    String execute() throws CommandException;
}
