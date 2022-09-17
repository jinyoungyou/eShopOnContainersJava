plugins {
    id("java")
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.freefair.lombok") version "6.5.0.2"
    id("com.google.cloud.tools.jib") version "3.2.1" apply false
    id("org.sonarqube") version "3.0" apply false
}

extra["springVersion"] = "5.3.22"
extra["springCloudVersion"] = "2021.0.3"
extra["assertJCoreVersion"] = "3.23.1"

tasks.bootJar {enabled = false}
tasks.jar {enabled = true}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "io.github.jinyoungyou"
    version = "1.0-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.bootJar {
        enabled = false
        isReproducibleFileOrder = true
    }
    tasks.jar {
        enabled = true
        isReproducibleFileOrder = true
    }

    repositories {
        mavenCentral()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }


    dependencies {
        // spring
        implementation("org.springframework.boot:spring-boot-starter-web")

        // guava
        implementation("com.google.guava:guava:31.1-jre")

        // lombok
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")

        testCompileOnly("org.projectlombok:lombok:1.18.24")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.24")

        testImplementation("org.assertj:assertj-core:3.6.1")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}