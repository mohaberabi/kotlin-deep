plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.mohaberabi.measured-processor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation("com.squareup:kotlinpoet:2.2.0")
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}