package com.kaushikam.aspect.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {
    "com.kaushikam.aspect.domain.model",
})
public class DomainConfig {
}
