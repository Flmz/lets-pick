plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val lombokVersion = "1.18.24"
val mapstructVersion = "1.5.3.Final"
val jUnitVersion = "5.9.0"
val springBootVersion = "3.0.0"

dependencies {
    //SPRING BOOT
    implementation("org.springframework.boot:spring-boot-starter-validation:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")

    //SWAGGER

    //POSTGRES
    runtimeOnly("org.postgresql:postgresql:42.5.1")

    //LOMBOK + MAPSTRUCT
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    implementation("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    //TESTS
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.0")
    testImplementation("org.testcontainers:postgresql:1.17.6")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
}

tasks.test{
    useJUnitPlatform()
}