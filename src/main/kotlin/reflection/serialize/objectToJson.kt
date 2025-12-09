package reflection.serialize

import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

fun objectToJson(
    any: Any,
    provideKey: (KProperty<*>) -> String = { it.name },
): String {
    return any::class
        .memberProperties
        .joinToString(
            prefix = "{",
            postfix = "}",
            transform = { property ->
                val string = buildString {
                    append("\"${provideKey(property)}\"")
                    append(":")
                    append(valueToJson(property.call(any)))
                }
                string
            }
        )
}



