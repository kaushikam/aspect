package com.kaushikam.aspect.infrastructure.repository;

import com.kaushikam.aspect.domain.model.User;
import com.kaushikam.aspect.domain.model.UserRepository;
import org.springframework.data.repository.Repository;

public interface UserRepositoryHibernate extends UserRepository, Repository<User, Long> { }
