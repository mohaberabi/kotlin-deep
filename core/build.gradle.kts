import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.2.20"
    id("dev.mokkery") version "3.0.0"
}

group = "org.mohaberabi.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":make-open"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
evaluationDependsOn(":make-open")
val enableAllOpenForTests = providers
    .gradleProperty("enableAllOpenForTests")
    .map { it.toBoolean() }
    .getOrElse(false)

if (enableAllOpenForTests) {
    val makeOpenJarTask = project(":make-open").tasks.named<Jar>("jar")
    val makeOpenJarFile = makeOpenJarTask.flatMap { it.archiveFile }
    tasks.withType<KotlinCompile>().configureEach {
        dependsOn(makeOpenJarTask)
        compilerOptions {
            freeCompilerArgs.add(
                makeOpenJarFile.map { jarFile ->
                    "-Xplugin=${jarFile.asFile.absolutePath}"
                }
            )
        }
    }

}


//tasks.withType<KotlinCompile>().matching {
//    it.name.contains("test", ignoreCase = true)
//}.configureEach {
//    dependsOn(makeOpenJarTask)
//    compilerOptions {
//        freeCompilerArgs.add(
//            makeOpenJarFile.map { jarFile ->
//                "-Xplugin=${jarFile.asFile.absolutePath}"
//            }
//        )
//    }
//}
