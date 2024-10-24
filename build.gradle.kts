plugins {
    java
    `maven-publish`
}

group = "io.github.unjoinable"
version = "1.0"


repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    dependencies {
        compileOnly("org.jetbrains:annotations:26.0.1")
        implementation("org.slf4j:slf4j-api:2.0.16")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "io.github.unjoinable.minigamelib"
                artifactId = "MinigameLib"
                version = "1.0"

                from(components["java"])
            }
        }
    }
}
