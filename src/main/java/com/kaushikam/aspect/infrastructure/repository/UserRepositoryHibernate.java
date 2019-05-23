package com.kaushikam.aspect.infrastructure.repository;

import com.kaushikam.aspect.domain.model.User;
import com.kaushikam.aspect.domain.model.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepositoryHibernate implements UserRepository {

    private EntityManager entityManager;

    public UserRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User persisted) {
        if (persisted.getId() == null) {
            entityManager.persist(persisted);
        } else {
            persisted = entityManager.merge(persisted);
        }

        return persisted;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(
                "SELECT u FROM User u",
                User.class)
                .getResultList();
    }
}
