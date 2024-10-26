dependencies {
    implementation(project(":"))
    compileOnly("net.minestom:minestom-snapshots:b0bad7e180")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }
}