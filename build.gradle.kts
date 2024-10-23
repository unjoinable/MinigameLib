plugins {
    id("java")
}

group = "io.github.unjoinable"
version = "1.0"

allprojects {
    apply(plugin = "java")

    java {
        withSourcesJar()
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:26.0.1")
        implementation("org.slf4j:slf4j-api:2.0.16")
    }
}
