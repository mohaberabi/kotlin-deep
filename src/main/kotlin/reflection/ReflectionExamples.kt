package reflection

import kotlin.reflect.full.memberProperties

fun displayPropertiesAsList(value: Any) {
    value::class.memberProperties
        .sortedBy { it.name }
        .map { "${it.name}: ${it.call(value)}" }
        .forEach(::println)
}

fun invokeReflection() {
    displayPropertiesAsList(Person("asdsad", "asdasd", 1, false))
    displayPropertiesAsList(Doggy("asdsad", 1))
    displayPropertiesAsList(DogBread.Labrador)

}

data class Person(
    val name: String,
    val surname: String,
    val children: Int,
    val female: Boolean
)

class Doggy(
    val name: String,
    val age: Int
)

enum class DogBread {
    Husky,
    Labrador,
    Pug,
    Border,

}