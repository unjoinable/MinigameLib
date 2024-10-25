plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.unjoinable"
version = "1.0"

dependencies {
    implementation(project(":common"))
    compileOnly("net.minestom:minestom-snapshots:b0bad7e180")
}
