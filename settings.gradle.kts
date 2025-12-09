plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"

}
rootProject.name = "KotlinAdvanced"

include(":generateinterface-annotations")
include(":generateinterface-processor")
include(":measured-processor")
include(":ksp-processor")
include(":make-open")
include(":core")