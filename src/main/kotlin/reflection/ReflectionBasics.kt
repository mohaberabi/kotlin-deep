package reflection

class ReflectionBasics {

    fun printKFunctionProps() {
//        val f1 = ::printABC
//        val f2 = ::double
//        val f3 = Complex::plus
//        val f4 = Complex::double
//        val f5 = Complex?::isNullOrZero
//        val f6 = FunctionReferences.Box<Int>::get
//        val f7 = FunctionReferences.Box<String>::set
//        println(f1.isInfix)
//        println(f1.isInline)
//        println(f1.name)
//        println(f1.annotations)
//        println(f1.isSuspend)
    }

    private fun invokeReflectionSimple2() {
        /**
         * “The result type from the function reference is an appropriate KFunctionX,
         * where X indicates the number of parameters.
         * This type also includes type parameters for each function parameter and the result.
         * For instance, the printABC reference type is KFunction0<Unit>. For method references,
         * a receiver is considered another parameter, so the Complex::double type is KFunction1<Complex, Complex>.”
         *     val f1: KFunction0<Unit> = ::printABC
         *     val f2: KFunction1<Int, Int> = ::double
         *     val f3: KFunction2<Complex, Number, Complex> = Complex::plus
         *     val f4: KFunction1<Complex, Complex> = Complex::double
         *     val f5: KFunction1<Complex?, Boolean> = Complex?::isNullOrZero
         *     val f6: KFunction1<Box<Int>, Int> = Box<Int>::get
         *     val f7: KFunction2<Box<String>, String, Unit>=Box<String>::set
         */
    }
}