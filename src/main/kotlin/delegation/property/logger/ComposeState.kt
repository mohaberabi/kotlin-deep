package delegation.property.logger

import kotlin.reflect.KProperty

class MutableStateOf<T>(
    private var value: T
) {
    operator fun getValue(
        thisRef: Any?,
        prop: KProperty<*>
    ): T = value

    operator fun setValue(
        thisRef: Any?,
        prop: KProperty<*>,
        newValue: T
    ) {
        value = newValue
    }
}

inline fun <reified T> mutableStateOf(init: T) = MutableStateOf(init)
fun mutableIntStateOf(init: Int) = MutableStateOf(init)
fun mutableFloatStateOf(init: Float) = MutableStateOf(init)


