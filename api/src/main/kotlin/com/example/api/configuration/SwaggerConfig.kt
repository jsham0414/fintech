package com.example.api.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun fintechApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("v0")
            .packagesToScan("com.example.api")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun fintechOpenAPI(): OpenAPI {
        return OpenAPI().info(Info().title("Fintech").description("대출 심사 API").version("v0.1"))
    }
}