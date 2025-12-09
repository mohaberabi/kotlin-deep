package reflection

import kotlin.reflect.KCallable
import kotlin.reflect.KParameter
import kotlin.reflect.KType

class KCallableExample {


    fun add(i: Int, j: Int): Int = i + j


    fun testMe() {
        val f1: KCallable<Int> = ::add
        val call1 = f1.call(1, 2)
        println(call1)
        runCatching {
            val call2 = f1.call("a", "b")
            println(call2)
        }.onFailure {
            it.printStackTrace()
        }
        val call3 = f1.callBy(
            mapOf(
                f1.parameters.first() to 1,
                f1.parameters[1] to 1,
            )
        )
        println(call3)

    }
}


val s = object : KParameter {
    override val index: Int
        get() = TODO("Not yet implemented")
    override val name: String?
        get() = TODO("Not yet implemented")
    override val type: KType
        get() = TODO("Not yet implemented")
    override val kind: KParameter.Kind
        get() = TODO("Not yet implemented")
    override val isOptional: Boolean
        get() = TODO("Not yet implemented")
    override val isVararg: Boolean
        get() = TODO("Not yet implemented")
    override val annotations: List<Annotation>
        get() = TODO("Not yet implemented")

}

