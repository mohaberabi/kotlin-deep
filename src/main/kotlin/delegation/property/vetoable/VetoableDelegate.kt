package org.mohaberabi.testideplugin.delegation.property.vetoable

import org.mohaberabi.testideplugin.delegation.property.observable.ObservableDelegate
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class VetoableDelegate<T>(
    private var value: T,
    private val shouldChange: (prop: KProperty<*>, prev: T, curr: T) -> Boolean
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
        val change = shouldChange(prop, value, newValue)
        if (change) {
            value = newValue
        }
    }
}

inline fun <reified T> vetoable(
    init: T,
    noinline shouldChange: (prop: KProperty<*>, prev: T, curr: T) -> Boolean
) = VetoableDelegate(value = init, shouldChange = shouldChange)


fun invokeVetoable() {
    var vetoable by vetoable(1) { prop, prev, curr ->
        println("${prop.name}-- prev-->$prev---curr-->$curr")
        val shouldChange = curr <= 100
        if (shouldChange.not()) {
            println("Skipped:${prop.name} as value is = $curr")
        }
        shouldChange
    }
    var kotlinVetoable by Delegates.vetoable(1) { prop, prev, curr ->
        println("${prop.name}-- prev-->$prev---curr-->$curr")
        val shouldChange = curr <= 100
        if (shouldChange.not()) {
            println("Skipped:${prop.name} as value is = $curr")
        }
        shouldChange
    }

    (90..150).map {
        vetoable += it
    }
    (90..150).map {
        kotlinVetoable += it

    }

}