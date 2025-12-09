package delegation.property.lazy


class LazyPuzzle {
    var x = 0
    val y by lazy { 1 / x }
    var zz = 1
    val z by lazy { zz }
    fun hello() {
        try {
            println(y)
        } catch (e: Exception) {
            x = 1
            println(y)
        }
    }

    fun hello2() {
        println(z)
        zz = 100
        println(z)
    }
}


fun invokeLazyPuzzle() {
    val puzzle = LazyPuzzle()
    puzzle.hello()
    /**
     * 1- integer division by zero is thrown
     * 2- catch gets invoked  but wait if the first time to evaluate throws exception the next time
     * you call the lazy value it will in return make the invocation again of the initializer until we
     * hit a safe value then it will be skipped see [LazyPuzzle.hello2]
     *
     */
    puzzle.hello2()
}