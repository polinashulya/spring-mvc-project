package com.example.dao.core.pool.impl;

import com.example.dao.core.pool.ConnectionPool;
import com.example.dao.core.pool.connection.ProxyConnection;
import com.example.dao.core.pool.exception.CannotGetJdbcConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DatabaseConnectionPool implements ConnectionPool {

    private static final Logger logger = LogManager.getLogger(DatabaseConnectionPool.class);

    private static DatabaseConnectionPool instance = new DatabaseConnectionPool();

    public static DatabaseConnectionPool getInstance() {
        return instance;
    }

    private DatabaseConnectionPool() {
    }

    /************************************************************************/

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static final int DEFAULT_INITIAL_POOL_SIZE = 10;

    public void init(final String driver, final String url,
                     final String user, final String password,
                     final String poolSize) {

        try {
            final int initialPoolSize = convertPoolSize(poolSize);
            //
            Class.forName(driver);

            availableConnections = new ArrayBlockingQueue<>(initialPoolSize);
            usedConnections = new ArrayBlockingQueue<>(initialPoolSize);

            for (int i = 0; i < initialPoolSize; i++) {
                Connection connection = createConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.add(proxyConnection);
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal(e);
            throw new CannotGetJdbcConnectionException(e);
        }
    }

    private int convertPoolSize(String poolSize) {
        try {
            return Integer.parseInt(poolSize);
        } catch (NumberFormatException ex) {
            logger.error("wrong pool size context param error!");
            return DEFAULT_INITIAL_POOL_SIZE;
        }
    }

    private static Connection createConnection(final String url,
                                               final String user,
                                               final String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public ProxyConnection getConnection() {
        return doGetConnection();
    }

    private ProxyConnection doGetConnection() {
        logger.info("\nPOOL FREE SIZE = " + availableConnections.size());
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = availableConnections.take();
            usedConnections.add(proxyConnection);
        } catch (InterruptedException ex) {
            logger.error(ex);
            throw new CannotGetJdbcConnectionException(ex);
        }
        return proxyConnection;
    }

    @Override
    public void putBackConnection(ProxyConnection connection) {
        availableConnections.add(connection);
        usedConnections.remove(connection);
    }

    @Override
    public void destroyPool() {
        usedConnections.forEach(ProxyConnection::destroy);
        availableConnections.forEach(ProxyConnection::destroy);
    }
}

