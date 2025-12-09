package delegation.interfaces

interface Parent {
    fun someFunction()
}

class Child : Parent {
    override fun someFunction() {
        println("${this::class.simpleName}")
    }
}

class AnotherChild : Parent by Child()
open class AnotherParent : Parent {
    override fun someFunction() {
        println("${this::class.simpleName}")
    }
}

class AnotherAnotherChild : AnotherParent()

fun printDelegateLimitations() {
    Child().someFunction()
    AnotherChild().someFunction()
    AnotherParent().someFunction()
    AnotherAnotherChild().someFunction()
    // Child
    // Child
    //AnotherParent
    //AnotherAnotherChild
}