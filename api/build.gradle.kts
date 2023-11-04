plugins {}

version = "0.0.1"

dependencies {
    // spring-boot-starter-web

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // test
    testImplementation("io.mockk:mockk:1.9.3")
    runtimeOnly("com.h2database:h2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")

    // AOP
    implementation("org.springframework.book:spring-boot-starter-aop")

    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")


    implementation(project(":domain"))
    // Kafka
    implementation(project(":kafka"))

    // Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}