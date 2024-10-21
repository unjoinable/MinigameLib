plugins {
    id("java")
}

group = "io.github.unjoinable"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.1")
    implementation("net.kyori:adventure-api:4.17.0")
}
