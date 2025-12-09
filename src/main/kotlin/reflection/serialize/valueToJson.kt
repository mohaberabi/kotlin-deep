package reflection.serialize

fun valueToJson(any: Any?): String = when (any) {
    null -> "$any"
    is Number -> "$any"
    is String, is Enum<*> -> "\"$any\""
    is Boolean -> "$any"
    is Iterable<*> -> iterableToJson(any)
    is Map<*, *> -> mapToJson(any)
    else -> objectToJson(any)
}
