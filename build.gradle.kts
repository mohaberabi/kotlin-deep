plugins {
    kotlin("jvm") version "2.2.20"
//    kotlin("kapt") version "2.2.20"
    id("com.google.devtools.ksp") version "2.2.20-2.0.4"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"

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
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
    implementation("io.gitlab.arturbosch.detekt:detekt-api:1.23.6")
    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.23.6")
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