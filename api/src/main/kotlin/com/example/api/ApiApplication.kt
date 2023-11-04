package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args); // 가변 인자로 변환됨
}