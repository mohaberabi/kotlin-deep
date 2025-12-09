package delegation.property.logger


var token: String? = null
    get() {
        println("token getter called $field")
        return field
    }
    set(value) {
        println("token value changed from $field  into $value")
        field = value
    }

var attempts: Int = 0
    get() {
        println("attempts getter called $field")
        return field
    }
    set(value) {
        println("attempts value changed from $field  into $value")
        field = value
    }

fun debugValueChangesLogger() {
    println(token)
    token = "token"
    println(token)
    token = "token1"
    println(token)
    token = "token2"
    println(token)
    //
    println(attempts)
    attempts = 1
    println(attempts)
    attempts = 2
    println(attempts)
    attempts = 3
    println(attempts)
}