plugins {
    id("java-library")
}

group = "io.github.unjoinable"
version = "1.0"


dependencies {
    implementation(project(":common"))
    implementation("net.minestom:minestom-snapshots:b0bad7e180")
}
