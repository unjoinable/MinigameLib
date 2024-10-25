plugins {
    java
    `maven-publish`
}
val projectVersion = "0.1.0"
group = "io.github.unjoinable"
version = projectVersion

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile>().configureEach {
    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

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
            }
        }
    }
}
