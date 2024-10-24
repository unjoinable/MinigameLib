plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.3"
}

group = "io.github.unjoinable"
version = "1.0"


dependencies {
    implementation(project(":common"))
    implementation("net.minestom:minestom-snapshots:b0bad7e180")
}
