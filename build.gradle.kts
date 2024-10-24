plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("maven-publish")
}

group = "io.github.unjoinable"
version = "1.0"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://jitpack.io")
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:26.0.1")
        implementation("org.slf4j:slf4j-api:2.0.16")
    }
}
