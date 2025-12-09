package reflection.serialize.model

import reflection.di.apply.testInjekt
import reflection.serialize.objectToJson
import kotlin.reflect.KProperty

class CreatureModel(
    val name: String,
    val attack: Int,
    val defense: Int,
    val isAlive: Boolean = true,
    val data: Map<String, Int> = mapOf(),
    val friends: List<CreatureModel> = listOf()
)

fun Any.toJson(
    provideKey: (KProperty<*>) -> String = { it.name }
): String = objectToJson(this, provideKey = provideKey)


fun testSerialization() {
    val base = CreatureModel(name = "mohab", attack = 100, defense = 100)

    val model = CreatureModel(
        name = "mohab",
        attack = 100,
        defense = 100,
        isAlive = false,
        friends = listOf(base, base, base),
        data = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
        )
    )

    val userBase = UserModel(
        name = "mohab",
        age = 27,
        salary = 1000000.500,
        roles = listOf(UserRoles.Admin),
        attrs = mapOf("title" to "senior"),
        isActive = true,
        squad = listOf()
    )

    val mohab = userBase.copy(squad = listOf(userBase, userBase, userBase))

    model.toJson().also { println(it); }

    mohab.toKJson().also { println(it) }

    testInjekt()
}