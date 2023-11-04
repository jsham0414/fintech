plugins {}

version = "0.0.1"

dependencies {
    // Kafka
    implementation(project(":kafka"))
    implementation("org.springframework.kafka:spring-kafka:3.0.10")
    implementation("com.fasterxml.jackson.module-kotlin:2.14.+")

    // Domain
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-web")
}