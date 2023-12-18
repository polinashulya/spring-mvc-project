package com.example.dao.impl;

import com.example.dao.CountryDao;
import com.example.dao.core.pool.connection.ConnectionWrapper;
import com.example.dao.core.pool.connection.ProxyConnection;
import com.example.dao.core.pool.impl.DatabaseConnectionPool;
import com.example.entity.Country;
import com.example.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {

    private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);

    @Override
    public List<Country> findAll() {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        List<Country> countries = new ArrayList<>();

        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

            statement = connectionWrapper.prepareStatement("SELECT c.id, c.name FROM countries c");
            ResultSet resultSet = statement.executeQuery();

            logger.debug("Executing query: {}", statement.toString());

            while (resultSet.next()) {
                Country country = getCountry(resultSet);
                countries.add(country);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        }

        return countries;
    }

    private static Country getCountry(ResultSet set) {
        try {
            return Country.builder()
                    .id(set.getLong(1))
                    .name(set.getString(2))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Country from ResultSet", e);
        }
    }


    @Override
    public Country getById(Long id) {

        String sql = "SELECT c.id, c.name FROM countries c where c.id = ? ";

        return super.getById(sql, id, CountryDaoImpl::getCountry);

    }

    @Override
    public Optional<Country> findById(Long id) {

        String sql = "SELECT c.id, c.name FROM countries c where c.id = ? ";

        return super.findById(sql, id, CountryDaoImpl::getCountry);
    }

    static class ConnectionCreator {

        private static final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

        static ProxyConnection getProxyConnection() {
            return pool.getConnection();
        }

        private ConnectionCreator() {
        }
    }

}
