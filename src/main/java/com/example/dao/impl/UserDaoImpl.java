package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.dao.core.pool.connection.ConnectionWrapper;
import com.example.dao.core.pool.connection.ProxyConnection;
import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final String FIND_ALL_USERS =
            "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                    "FROM users u " +
                    "join countries c on u.country_id = c.id " +
                    "WHERE u.deleted = 'false' ";
    private static final String SORT_TYPE_ASC = "ASC";
    private static final String SORT_USERS_BY_ID = "byId";
    private static final String SORT_USERS_BY_SURNAME = "bySurname";
    private static final String SORT_USERS_BY_LOGIN = "byLogin";
    private static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    private static final String PAGINATION_DEFAULT = " LIMIT " + 5 + " OFFSET " + 0;

    private static final String TOTAL_COUNT_USERS =
            "SELECT COUNT(*) AS totalUsers " +
                    "FROM users u " +
                    "join countries c on u.country_id = c.id " +
                    "WHERE u.deleted = 'false' ";


    @Override
    public List<User> findAll() {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        List<User> users = new ArrayList<>();

        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

            statement = connectionWrapper.prepareStatement(FIND_ALL_USERS);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            while (set.next()) {
                User user = getUser(set);

                users.add(user);
            }


        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        }

        return users;
    }

    @Override
    public List<User> findAll(String filterAndSearchsql, String sortSql, String page, String pageSize) {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;


        String paginationSql;
        int offset = 0;

        if (page != null && !page.isEmpty() && pageSize != null && !pageSize.isEmpty()) {

            offset = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
            paginationSql = " LIMIT " + pageSize + " OFFSET " + offset;

        } else {
            paginationSql = PAGINATION_DEFAULT;
        }


        List<User> users = new ArrayList<>();

        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

            statement = connectionWrapper.prepareStatement(FIND_ALL_USERS + filterAndSearchsql + sortSql + paginationSql);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            while (set.next()) {
                User user = getUser(set);

                users.add(user);
            }


        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        }

        return users;
    }

    @Override
    public User getById(Long id) {

        String sql = "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                "FROM users u join countries c on u.country_id = c.id where u.id = ? ";

        return super.getById(sql, id, UserDaoImpl::getUser);

    }

    @Override
    public Optional<User> findById(Long id) {

        String sql = "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                "FROM users u join countries c on u.country_id = c.id where u.id = ?";

        return super.findById(sql, id, UserDaoImpl::getUser);
    }

    @Override
    public User getByLogin(String login) {

        String sql = "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                "FROM users u join countries c on u.country_id = c.id where u.login = ?";

        return super.getByLogin(sql, login, UserDaoImpl::getUser);
    }

    @Override
    public Optional<User> findByLogin(String login) {

        String sql = "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                "FROM users u join countries c on u.country_id = c.id where u.login = ?";

        return super.findByLogin(sql, login, UserDaoImpl::getUser);
    }

    private static User getUser(ResultSet set) {
        try {
            return User.builder()
                    .id(set.getLong(1))
                    .login(set.getString(2))
                    .firstname(set.getString(3))
                    .surname(set.getString(4))
                    .birthDate((set.getDate(5)).toLocalDate())
                    .banned(set.getBoolean(6))
                    .country(
                            Country.builder()
                                    .id(set.getLong(7))
                                    .name(set.getString(8))
                                    .build()
                    )
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping User from ResultSet", e);
        }
    }


    @Override
    public void save(User user) {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

            statement = connectionWrapper.prepareStatement("INSERT INTO users (login, password, firstname, surname, birth_date, banned, deleted, country_id ) VALUES (?,?,?,?,?,?,?, ?);");

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getSurname());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setBoolean(6, user.isBanned());
            statement.setBoolean(7, user.isDeleted());
            statement.setLong(8, user.getCountry().getId());

            statement.executeUpdate();
            logger.debug("Executing query: {}", statement.toString());

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred while executing query: {}", statement.toString(), ex);
            throw new DAOException("An SQL exception occurred while executing query", ex);
        }

    }


    @Override
    public void delete(Long id) {

        String deleteSql = "UPDATE users u SET deleted=true where u.id = ?";

        String findSql = "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                "FROM users u join countries c on u.country_id = c.id where u.id = ? ";

        super.delete(deleteSql, findSql, id, UserDaoImpl::getUser);
    }

    private static String getSortByOrDefault(String sortBy) {
        return sortBy == null ? "default" : sortBy;
    }

    @Override
    public String getFilterAndSearchSql(String countryId, String search) {

        String sql = new String();
        String filterSql = new String();
        String searchSql = new String();

        if (countryId != null && !countryId.isEmpty()) {
            filterSql = " AND u.country_id = " + countryId;
        }

        if (search != null && !search.isEmpty()) {
            searchSql = " AND (u.login LIKE '%" + search + "%'" +
                    " OR u.firstname LIKE '%" + search + "%'" +
                    " OR u.surname LIKE '%" + search + "%'" +
                    " OR c.name LIKE '%" + search + "%')";
        }

        sql = filterSql + searchSql;

        return sql;
    }

    @Override
    public String getSortingSql(String sortBy, String sortType) {

        switch (getSortByOrDefault(sortBy)) {
            case SORT_USERS_BY_LOGIN -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.login ASC" :
                        " ORDER BY u.login DESC";
            }
            case SORT_USERS_BY_SURNAME -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.surname ASC" :
                        " ORDER BY u.surname DESC";
            }
            case SORT_USERS_BY_BIRTH_DATE -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.birth_date ASC" :
                        " ORDER BY u.birth_date DESC";
            }
            case SORT_USERS_BY_ID -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.id ASC" :
                        " ORDER BY u.id DESC";
            }
            default -> {
                return " ORDER BY u.id ASC";
            }
        }
    }

    @Override
    public int getTotalResult(String filterAndSearchsql) {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;

        int totalResult = 0;

        try {
            proxyConnection = CountryDaoImpl.ConnectionCreator.getProxyConnection();
            ConnectionWrapper connectionWrapper = proxyConnection.getConnectionWrapper();

            statement = connectionWrapper.prepareStatement(TOTAL_COUNT_USERS + filterAndSearchsql);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            if (set.next()) {
                totalResult = set.getInt("totalUsers");
            } else {
                logger.warn("The total result of users is null");
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred while executing query: {}", statement.toString(), ex);
            throw new DAOException("An SQL exception occurred while executing query", ex);
        }

        return totalResult;
    }

}
