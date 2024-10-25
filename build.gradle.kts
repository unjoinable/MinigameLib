plugins {
    java
    `maven-publish`
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    group = "io.github.unjoinable"
    version = "0.1.0"

    java {
        toolchain.languageVersion = JavaLanguageVersion.of(21)
        withSourcesJar()
        withJavadocJar()
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.16")
        compileOnly("net.kyori:adventure-api:4.17.0")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }

        repositories {
            mavenLocal()
        }
    }
}