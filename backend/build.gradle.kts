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
    implementation("io.springfox:springfox-boot-starter:${springBootVersion}")
    //POSTGRES
    runtimeOnly("org.postgresql:postgresql:42.5.1")


    //LOMBOK + MAPSTRUCT
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    implementation("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    //TESTS
//    testImplementation("org.junit.jupiter:junit-jupiter-api:${jUnitVersion}")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${jUnitVersion}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}"){
        exclude(module = "junit")
    }

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}