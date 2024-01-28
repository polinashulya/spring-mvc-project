package com.example.dao.impl;

import com.example.dao.UserRoleDao;
import com.example.entity.CountryEntity;
import com.example.entity.UserRoleEntity;
import com.example.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserRoleDaoImpl implements UserRoleDao {

    private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);

    private final SessionFactory sessionFactory;

    public UserRoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserRoleEntity getByName(String name) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            String hql = "FROM UserRoleEntity c WHERE c.name = :name";

            return session.createQuery(hql, UserRoleEntity.class)
                    .setParameter("name", name)
                    .uniqueResult();

        } catch (Exception e) {
            logger.error("Error occurred while retrieving entity by county name", e);
            throw new DAOException("Error occurred while retrieving entity by county name", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Optional<UserRoleEntity> findByName(String name) {
        return  Optional.ofNullable(getByName(name));
    }

}
