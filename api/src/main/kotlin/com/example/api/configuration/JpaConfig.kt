package com.example.api.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@ComponentScan(basePackages = ["com.example"])
@EntityScan(basePackages = ["com.example.domain"])
@EnableJpaRepositories(basePackages = ["com.example.domain"])
class JpaConfig {

}