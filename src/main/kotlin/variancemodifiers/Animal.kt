//package org.mohaberabi.testideplugin.variancemodifiers
//
//import javax.print.Doc
//
//interface Animal {
//    fun pet()
//}
//
//class Cat(val name: String) : Animal {
//    override fun pet() {
//        println("iam eating")
//    }
//}
//
//class Dog(val name: String) : Animal {
//    override fun pet() {
//        println("iam eating")
//    }
//}
//
//fun petAnimals(animals: List<Animal>) {
//    for (animal in animals) {
//        animal.pet()
//    }
//}
//
//fun addAnimal(animals: MutableList<Animal>) {
//    animals.add(Dog("cc"))
//}
//
//fun testPetAnimals() {
//    val cats: List<Cat> = listOf<Cat>(Cat("a"), Cat("b"))
//    petAnimals(cats)
//    val dogs: MutableList<Dog> = mutableListOf<Dog>()
//    addAnimal(dogs)
//}
