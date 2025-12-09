plugins {
    kotlin("jvm") version "2.2.20"
//    kotlin("kapt") version "2.2.20"
    id("com.google.devtools.ksp") version "2.2.20-2.0.4"
}

group = "org.mohaberabi.testideplugin"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation("com.squareup:javapoet:1.13.0")
//    implementation(project(":generateinterface-annotations"))
    ksp(project("ksp-processor"))
    implementation(project("ksp-processor"))

//    kapt(project(":generateinterface-processor"))
//    implementation(project(":measured-processor"))
//    kapt(project(":measured-processor"))

    implementation(project(":core"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}