plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation(project(":common"))
    compileOnly("net.minestom:minestom-snapshots:b0bad7e180")
}
