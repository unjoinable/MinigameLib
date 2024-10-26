plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.3"
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.gradleup.shadow")

    group = "io.github.unjoinable"
    version = "0.1.0"

    java {
        toolchain.languageVersion = JavaLanguageVersion.of(21)
        withSourcesJar()
        withJavadocJar()
    }

    tasks {
        build {
            dependsOn(shadowJar)
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.16")
        compileOnly("net.kyori:adventure-api:4.17.0")
    }

    afterEvaluate {
        publishing {
            publications {
                create<MavenPublication>("Library") {
                    from(components["java"])
                    artifact(tasks["sourceJar"])
                    tasks.findByName("shadowJar")?.let {
                        artifact(tasks["shadowJar"])
                    }
                }

//                create<MavenPublication>("shadow") {
//                    artifact(tasks.named("shadowJar").get())
//                }
//            create<MavenPublication>("maven") {
//                from(components["java"])
//            }
            }
            repositories {
                mavenLocal()
            }
        }
    }
}