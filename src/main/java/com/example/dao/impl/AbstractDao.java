package com.example.dao.impl;

import com.example.dao.core.pool.connection.ConnectionWrapper;
import com.example.dao.core.pool.connection.ProxyConnection;
import com.example.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractDao<T> {

    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

    public T getById(String sql, Long id, Function<ResultSet, T> function) {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        T t = null;

        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

            statement = connectionWrapper.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            logger.info(resultSet);

            if (resultSet.next()) {
                t = function.apply(resultSet);
            } else {
                logger.warn("No item found by id " + id);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        }

        return t;
    }

    public T getByLogin(String sql, String login, Function<ResultSet, T> function) {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        T t = null;
        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();
            statement = connectionWrapper.prepareStatement(sql);

            statement.setString(1, login);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            if (set.next()) {
                t = function.apply(set);
            } else {
                logger.warn("No item found with by login " + login);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        }

        return t;
    }

    public Optional<T> findById(String sql, Long id, Function<ResultSet, T> function) {
        return Optional.ofNullable(getById(sql, id, function));
    }


    public Optional<T> findByLogin(String sql, String login, Function<ResultSet, T> function) {
        return Optional.ofNullable(getByLogin(sql, login, function));
    }

    public void delete(String deleteSql, String findSql, Long id, Function<ResultSet, T> function) {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            if (findById(findSql, id, function).isPresent()) {
                proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
                ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

                statement = connectionWrapper.prepareStatement(deleteSql);

                statement.setLong(1, id);

                statement.executeUpdate();
                logger.debug("Executing query: {}", statement.toString());

            } else {
                logger.debug("Item with id = " + id + " was not found!");
                throw new DAOException("Item with id = " + id + " was not found!");
            }
        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            System.err.println();
            throw new DAOException(ex);
        }

    }

}
