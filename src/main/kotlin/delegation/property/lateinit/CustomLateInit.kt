package delegation.property.lateinit

import kotlin.reflect.KProperty

class LateInit<T>() {

    private var value: T? = null

    operator fun getValue(
        thisRef: Any?,
        prop: KProperty<*>
    ): T {
        return value ?: error("${prop.name} is not yet initialized")
    }

    operator fun setValue(
        thisRef: Any?,
        prop: KProperty<*>,
        newValue: T
    ) {
        if (value != null) return
        value = newValue
    }
}

fun <T> lateInit(): LateInit<T> = LateInit<T>()