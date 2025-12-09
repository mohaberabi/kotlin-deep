package reflection.di.apply

import reflection.di.injekt
import reflection.di.sample.ClassA
import reflection.di.sample.ClassB
import reflection.di.sample.ClassC
import reflection.di.sample.ComplexSingleTonClass
import reflection.di.sample.FactoryClass
import reflection.di.sample.SingletonClass


val injektModule = injekt {
    singleton { ClassA() }
    singleton { ClassB() }
    singleton { ClassC() }
    factory { FactoryClass(get(), get(), get()) }
    singleton { ComplexSingleTonClass(get(), get(), get(), get()) }
    singleton { SingletonClass() }
}


fun testInjekt() {
    val classA = injektModule.get<ClassA>()
    val classB = injektModule.get<ClassB>()
    val classC = injektModule.get<ClassC>()
    val factory = injektModule.get<FactoryClass>()
    val complexSingleTon = injektModule.get<ComplexSingleTonClass>()
    val singleTone = injektModule.get<SingletonClass>()
    println(classA.value())
    println(classB.value())
    println(classC.value())
    println(factory.invoke())
    println(complexSingleTon.invoke())
    println(singleTone.value)
    val factory2 = injektModule.get<FactoryClass>()
}