package com.example.dao.impl;

import com.example.dao.CountryDao;
import com.example.entity.CountryEntity;
import com.example.exception.DAOException;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CountryDaoImpl extends AbstractDaoImpl<CountryEntity> implements CountryDao {

    private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);


    @Override
    public List<CountryEntity> findAll() {

        Session session = null;

        try {
            session = sessionFactory.openSession();

            String hql = "FROM CountryEntity";
            TypedQuery<CountryEntity> query = session.createQuery(hql, CountryEntity.class);

            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all countries", e);
            throw new DAOException("Error occurred while retrieving all countries", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Optional<CountryEntity> findById(Long id) {
        return Optional.ofNullable(
                getById(GET_COUNTRY_BY_ID, id)
        );
    }

    @Override
    public CountryEntity getByName(String name) {
        return executeQuery(session -> {
            String hql = "FROM CountryEntity c WHERE c.name = :name";
            return session.createQuery(hql, CountryEntity.class)
                    .setParameter("name", name)
                    .uniqueResult();
        });
    }

    @Override
    public Optional<CountryEntity> findByName(String name) {
        return Optional.ofNullable(getByName(name));
    }


    private static final String GET_COUNTRY_BY_ID = "FROM CountryEntity c WHERE c.id = :id";
}
