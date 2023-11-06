plugins {
    kotlin("plugin.jpa")
}

version = "0.0.1"

allprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.6")
        runtimeOnly("mysql:mysql-connector-java")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        implementation("org.springframework.boot:spring-boot-starter-aop:2.7.6")
    }
}