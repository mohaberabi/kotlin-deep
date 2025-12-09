package reflection

import kotlin.reflect.KCallable

operator fun String.times(times: Int) = this.repeat(times)
fun double(i: Int): Int = i * 2
fun printABC() {
    println("ABC")
}

class Complex(val real: Double, val imaginary: Double) {
    fun plus(number: Number): Complex = Complex(
        real = real + number.toDouble(),
        imaginary = imaginary
    )
}

private fun nonZeroDoubled(
    numbers: List<Complex?>,
): List<Complex?> = numbers
    .filterNot(Complex?::isNullOrZero)
    .filterNotNull()
    .map(Complex::double)


fun Complex.double(): Complex =
    Complex(real * 2, imaginary * 2)

fun Complex?.isNullOrZero(): Boolean =
    this == null || (this.real == 0.0 && this.imaginary == 0.0)

class FunctionReferences {

    class Box<T>(var value: T) {
        fun get(): T = value
    }

    fun <T> Box<T>.set(value: T) {
        this.value = value
    }

    fun printMe() {
        val f: KCallable<String> = String::times
        println(f.name) // times
        println(f.parameters.map { it.name }) // [null, times]
        println(f.returnType) // kotlin.String
        println(f.typeParameters) // []
        println(f.visibility) // PUBLIC
        println(f.isFinal) // true
        println(f.isOpen) // false
        println(f.isAbstract) // false
        println(f.isSuspend) // false”
    }
}