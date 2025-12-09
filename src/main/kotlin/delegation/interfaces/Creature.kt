//package org.mohaberabi.testideplugin.delegation.interfaces
//
//interface Creature {
//
//    val power: Int
//    val defensePower: Int
//    fun attack()
//}
//
//class GenericCreature : Creature {
//    override val power: Int = 100
//    override val defensePower: Int = 100
//    override fun attack() {
//        println("attacking with power $power"); }
//}
//
//class Goblin : Creature {
//    val delegate = GenericCreature()
//    override val power: Int = delegate.power
//    override val defensePower: Int = delegate.defensePower
//    override fun attack() {
//        delegate.attack()
//    }
//}
//
//interface Attacker {
//    fun attackOther()
//}
//
//interface Defender {
//    fun defend()
//}
//
//class Hulk : Defender by object : Defender {
//    override fun defend() {
//    }
//}, Attacker by object : Attacker {
//    override fun attackOther() {
//
//    }
//}
//
//class Thanos : Creature by Goblin()
//class SpiderMan(private val delegate: Creature) : Creature by delegate