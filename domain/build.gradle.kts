plugins {
    kotlin("plugin.jpa")
}

version = "0.0.1"

allprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.mysql:mysql-connector-j:8.0.33")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
    }
}