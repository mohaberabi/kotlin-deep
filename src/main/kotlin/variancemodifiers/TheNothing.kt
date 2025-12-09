package variancemodifiers


sealed class LinkedList<T>
data class Node<T>(
    val head: T,
    val tail: LinkedList<T>
) : LinkedList<T>()

class Empty<T> : LinkedList<T>()

object EmptyLinkedList : LinkedList<Nothing>()

fun testLinkedList() {
    val strs = Node("a", Node("aa", Empty()))
    val ints = Node(1, Node(2, Empty()))
    val empty: LinkedList<Char> = Empty()
}

sealed class Either<out L, out R>
data class Left<L>(val value: L) : Either<L, Nothing>()
data class Right<R>(val value: R) : Either<Nothing, R>()

fun testEither() {

    val result = createEither(1)
    if (result is Right) {
        result.value
    }
    if (result is Left) {
        result.value
    }
    when (result) {
        is Left<*> -> TODO()
        is Right<*> -> TODO()
    }
}

fun createEither(number: Int): Either<Exception, String> {
    if (number <= 0) return Left(Exception("number is small"))
    return Right("hello")
}