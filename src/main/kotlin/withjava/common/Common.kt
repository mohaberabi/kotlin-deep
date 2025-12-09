//@file:JvmName("Overloads")
//
//package withjava.common
//
//import java.io.IOException
//import kotlin.jvm.Throws
//
//
//class Common {
//
//    @JvmOverloads
//    fun test(
//        name: String = "",
//        age: Int = 1,
//        email: String = ""
//    ) {
//    }
//
//    fun lambda(block: (Int, String, Double) -> Unit) {
//        block(11, "", 1.0)
//    }
//
//    @Throws(IOException::class)
//    fun mightThrow() {
//    }
//}
//
//
