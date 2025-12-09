package delegation.property.logger

import kotlin.reflect.KProperty

/**
 * // Code in Kotlin:
 * var token: String? by LoggingProperty(null)
 *
 * // What it is compiled to when a property is top-level
 * @JvmField
 * private val `token$delegate` = LoggingProperty<String?>(null)
 * var token: String?
 *     get() = `token$delegate`.getValue(null, ::token)
 *     set(value) {
 *         `token$delegate`.setValue(null, ::token, value)
 *     }
 *
 * // What it is compiled to when a property is in a class
 * @JvmField
 * private val `token$delegate` = LoggingProperty<String?>(null)
 * var token: String?
 *     get() = `token$delegate`.getValue(this, this::token)
 *     set(value) {
 *         `token$delegate`.setValue(this, this::token, value)
 *     }
 *
 */
class AccessPropertyLogger<T>(
    var value: T,
) {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        println("${prop.name} getter returned ${value}")
        return value
    }

    operator fun setValue(
        thisRef: Any?,
        prop: KProperty<*>,
        newValue: T
    ) {
        println("${prop.name} changed from $value to $newValue")
        value = newValue
    }
}

fun testAccessLoggerDelegation() {
    var token by AccessPropertyLogger("")
    var attempts by AccessPropertyLogger(1)

    repeat(10) {
        if (it != 0) {
            token += it
            attempts *= it
        }

    }
}