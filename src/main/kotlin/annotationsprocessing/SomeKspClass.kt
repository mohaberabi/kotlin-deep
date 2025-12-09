package annotationsprocessing

import kspprocessor.annotations.GenerateKspInterface


@GenerateKspInterface("SomeKspInterface")
class SomeKspClassImpl : SomeKspInterface {


    override fun function1(name: String) {}
    override fun function2(name: String, age: Int, salarry: Double) {}

}