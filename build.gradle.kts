plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val projectVersion = "0.1.0"

tasks.withType<JavaCompile>().configureEach {
    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

allprojects {
    group = "io.github.unjoinable"
    version = projectVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.16")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "io.github.unjoinable.minigamelib"
                artifactId = project.name
                version = projectVersion

                from(components["java"])
                artifact(tasks["shadowJar"])
            }
        }
    }
}
