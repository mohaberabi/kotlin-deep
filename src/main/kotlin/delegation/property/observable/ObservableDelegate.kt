package org.mohaberabi.testideplugin.delegation.property.observable

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ObservableDelegate<T>(
    private var value: T,
    private val onChanged: (prop: KProperty<*>, prev: T, curr: T) -> Unit
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
        onChanged(prop, value, newValue)
        value = newValue
    }
}

inline fun <reified T> observable(
    init: T,
    noinline onChanged: (prop: KProperty<*>, prev: T, curr: T) -> Unit
) = ObservableDelegate(value = init, onChanged = onChanged)


fun invokeObservable() {
    var observable by observable("mohab") { prop, prev, curr ->
        println("${prop.name}--- prev::$prev --- curr::$curr")
    }
    var kotlinObservable by Delegates.observable("mohab") { prop, prev, curr ->
        println("${prop.name}--- prev::$prev --- curr::$curr")
    }
    repeat(10) {
        kotlinObservable += it
    }
    repeat(10) {
        observable += it
    }
}