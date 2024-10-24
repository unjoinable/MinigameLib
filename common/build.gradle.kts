plugins {
    java
    `maven-publish`
}

group = "io.github.unjoinable"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.kyori:adventure-api:4.17.0")
    compileOnly("org.jetbrains:annotations:26.0.1")
    implementation("org.slf4j:slf4j-api:2.0.16")
}
