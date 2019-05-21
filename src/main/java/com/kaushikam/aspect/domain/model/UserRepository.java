package com.kaushikam.aspect.domain.model;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User persisted);
    Optional<User> findById(Long id);
    List<User> findAll();
}
