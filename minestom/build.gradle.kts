plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.unjoinable"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

dependencies {
    implementation(project(":common"))
    compileOnly("net.minestom:minestom-snapshots:b0bad7e180")
    compileOnly("org.jetbrains:annotations:26.0.1")
    implementation("org.slf4j:slf4j-api:2.0.16")
}
