package kspprocessor.annotations

@Target(AnnotationTarget.CLASS)
annotation class GenerateKspInterface(
    val name: String
)
