package delegation.interfaces


interface Attack {
    val attack: Int
    val defend: Int
}

interface Defense {
    val defend: Int
}

class Thanos : Attack {
    override val attack = 100
    override val defend = 100
}

class DarkKnight : Defense {
    override val defend = 50
}

class SuperMan(
    private val attacker: Attack,
    private val defender: Defense
) : Attack by attacker, Defense by defender {
    override val defend: Int = attacker.defend - defender.defend
}