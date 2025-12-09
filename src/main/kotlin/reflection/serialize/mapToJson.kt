package reflection.serialize

fun mapToJson(map: Map<*, *>): String = map.toList().joinToString(
    prefix = "{",
    postfix = "}",
    transform = { (key, value) ->
        val string = buildString {
            append("\"${key}\"")
            append(":")
            append(valueToJson(value))
        }

        string
    }
)