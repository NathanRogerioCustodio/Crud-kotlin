plugins {
    kotlin("jvm") version "1.8.22"
    application
}

application {
    mainClass.set("MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
}
