plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //SPRING BOOT
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.0")

    //SWAGGER
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    //POSTGRES
    runtimeOnly("org.postgresql:postgresql:42.5.1")


    //LOMBOK + MAPSTRUCT
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    //TESTS
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}