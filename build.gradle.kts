plugins {
    id("java")
}

group = "io.github.unjoinable"
version = "1.0"

subprojects {

    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }

    dependencies {
        apply(plugin = "java")
        compileOnly("org.jetbrains:annotations:26.0.1")
        implementation("org.slf4j:slf4j-api:2.0.16")
    }

}
