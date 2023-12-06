package com.example.dao.core.pool.listener;

import com.example.dao.core.pool.impl.DatabaseConnectionPool;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "pool.size";


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();
        final String driver = servletContext.getInitParameter(DRIVER);
        final String url = servletContext.getInitParameter(URL);
        final String user = servletContext.getInitParameter(USER);
        final String password = servletContext.getInitParameter(PASSWORD);
        final String poolSize = servletContext.getInitParameter(POOL_SIZE);
        //
        final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();
        pool.init(driver, url, user, password, poolSize);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DatabaseConnectionPool.getInstance().destroyPool();
    }
}
