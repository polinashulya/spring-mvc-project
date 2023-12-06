package com.example.servlet;


import com.example.servlet.factory.CommandFactory;
import com.example.servlet.factory.impl.CommandFactoryImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/mainWindow")
public class MainServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    public static final String ACTION_TYPE = "action";
    public static final String ERROR_PAGE = "error_page";

    private final CommandFactory commandFactory;

    public MainServlet() {
        this.commandFactory = new CommandFactoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = getActionType(req);
        logger.info("DO GET command name  " + action);

        try {
            final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            final Command command = commandFactory.createCommand(action, req, resp);
            final String path = command.execute();

            req.getRequestDispatcher(path).forward(req, resp);
        } catch (Exception e) {
            logger.error("Exception caught: ", e);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error_page.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = getActionType(req);
        logger.info("DO POST command name  " + action);

        try {
            final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            final Command command = commandFactory.createCommand(action, req, resp);
            final String path = command.execute();

            resp.sendRedirect(path);
        } catch (Exception e) {
            logger.error("Exception caught: ", e);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error_page.jsp").forward(req, resp);

        }

    }

    private String getActionType(HttpServletRequest req) {
        String action = req.getParameter(ACTION_TYPE);

        if (action == null || !commandFactory.isValidAction(action)) {
            req.setAttribute(ERROR_PAGE, "Invalid or missing action");
            return "error_page";
        }

        return action;
    }


}
