import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "me.creek"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

application {
    mainClass.set("MainKt")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
