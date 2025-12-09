package reflection.serialize

fun iterableToJson(
    iterable: Iterable<*>,
): String = iterable.joinToString(prefix = "[", postfix = "]", transform = ::valueToJson)
