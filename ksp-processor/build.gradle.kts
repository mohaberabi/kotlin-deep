plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.mohaberabi.ksp-processor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}
buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "2.2.20"))
    }
}
dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation("com.squareup:kotlinpoet:2.2.0")
    implementation("com.squareup:kotlinpoet-ksp:2.2.0")
    implementation(kotlin("stdlib"))
    implementation("com.google.devtools.ksp:symbol-processing-api:2.2.10-2.0.2")
    testImplementation("com.github.tschuchortdev:kotlin-compile-testing:1.6.0")
    testImplementation("com.github.tschuchortdev:kotlin-compile-testing-ksp:1.6.0")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}