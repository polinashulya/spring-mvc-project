package com.example.dao.core.pool.connection;

import com.example.dao.core.pool.ConnectionPool;
import com.example.dao.core.pool.impl.DatabaseConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ProxyConnection implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(ProxyConnection.class);
    private ConnectionWrapper connectionWrapper;

    private ConnectionPool pool = DatabaseConnectionPool.getInstance();


    public ProxyConnection(Connection connection) {
        this.connectionWrapper = new ConnectionWrapper(connection);
    }

    @Override
    public void close() {
        pool.putBackConnection(this);
    }


    public ConnectionWrapper getConnectionWrapper() {
        return connectionWrapper;
    }


    public void destroy() {
        try {
            this.connectionWrapper.realClose();
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }
}
