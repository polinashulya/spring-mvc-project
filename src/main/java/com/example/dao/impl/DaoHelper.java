package com.example.dao.impl;

import com.example.dao.core.pool.connection.ProxyConnection;
import com.example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DaoHelper {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    static void closeConnection(ProxyConnection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception ex) {

            }
        }
    }
}
