package reflection.serialize.annotations

@Target(AnnotationTarget.CLASS)
annotation class KJson()


@Target(AnnotationTarget.PROPERTY)
annotation class KJsonName(
    val jsonKey: String
)
