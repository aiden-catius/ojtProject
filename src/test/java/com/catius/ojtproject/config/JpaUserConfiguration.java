package com.catius.ojtproject.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.catius.ojtproject.device.repository")
@EntityScan("com.catius.ojtproject")
public class JpaUserConfiguration {
}
