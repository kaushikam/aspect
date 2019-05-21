package com.kaushikam.aspect.spring.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
        "com.kaushikam.aspect.infrastructure.repository"
})
public class PersistenceConfig {
}
