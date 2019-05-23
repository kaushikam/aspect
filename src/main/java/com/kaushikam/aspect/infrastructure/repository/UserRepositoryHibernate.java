package com.kaushikam.aspect.infrastructure.repository;

import com.kaushikam.aspect.domain.model.User;
import com.kaushikam.aspect.domain.model.UserRepository;
import com.kaushikam.aspect.infrastructure.repository.utils.HibernateUtilities;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserRepositoryHibernate implements UserRepository {
    @Override
    public void save(User persisted) {
        Session session = null;
        try {
            session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();
            Long id = (Long) session.save(persisted);
            persisted.setId(id);
            session.flush();
        } catch (HibernateException e) {
            try {
                if (session != null) {
                    session.getTransaction().rollback();
                }
            } catch (RuntimeException rExc) {
                throw new PersistenceException("Rollback not possible", rExc);
            }
            throw  new PersistenceException("Unknown error", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Session session = null;
        User user;
        try {
            session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();
            user = session.load(User.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            try {
                if (session != null)
                    session.getTransaction().rollback();
            } catch (RuntimeException exc) {
                throw new PersistenceException("Rollback impossible!", exc);
            }
            throw new PersistenceException("Unknown error", e);
        } finally {
            if (session != null)
                session.close();
        }

        return user == null ? Optional.empty() : Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        Session session = null;
        try {
            session = HibernateUtilities.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> rootEntry = criteriaQuery.from(User.class);
            CriteriaQuery<User> all = criteriaQuery.select(rootEntry);

            TypedQuery<User> allQuery = session.createQuery(all);
            return allQuery.getResultList();
        } catch (HibernateException e) {
            try {
                if (session != null)
                    session.getTransaction().rollback();
            } catch (RuntimeException exc) {
                throw new PersistenceException("Rollback impossible!", exc);
            }
            throw new PersistenceException("Unknown error", e);
        } finally {
            if (session != null)
                session.close();
        }
    }
}
