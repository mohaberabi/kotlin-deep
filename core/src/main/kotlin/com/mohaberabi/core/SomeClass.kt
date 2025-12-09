package com.mohaberabi.core

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class SomeClass {
    fun printMe(): Long = 201098
}


class DepA {
    fun getValue(): Int = 1
}

class DepB {
    fun getValue(): Int = 2
}

class DepC {
    fun getValue(): Int = 3
}

class ClassUnderTest(
    private val depA: DepA,
    private val depB: DepB,
    private val depC: DepC
) {

    fun getTotal() = depA.getValue() + depB.getValue() + depC.getValue()
}

class SuspendedClass {
    suspend fun getValue(): Int {
        delay(3.seconds)
        return 500
    }
}
