//package org.mohaberabi.testideplugin.variancemodifiers
//
//class AnimalBox<out T>
//class AnimalBoxContra<in T>
//
//class Box<T>
//
//open class Dog
//class Puppy : Dog()
//
//fun varianceProblem() {
//    val dog: Dog = Puppy()
//    val boxDog: Box<Dog> = Box<Puppy>()
//    val puppyBox: Box<Puppy> = Box<Dog>()
//    val boxNumber: Box<Number> = Box<Int>()
//    val boxInteger: Box<Int> = Box<Number>()
//}
//
//
//fun varianceSolution() {
//    val dog: Dog = Puppy()
//    val boxDog: AnimalBox<Dog> = AnimalBox<Puppy>()
//    val boxNumber: AnimalBox<Number> = AnimalBox<Int>()
//}
//
//fun varianceSolution2() {
//    val dog: Dog = Puppy()
//    val boxDog: AnimalBoxContra<Dog> = AnimalBoxContra<Puppy>()
//    val boxNumber: AnimalBoxContra<Number> = AnimalBoxContra<Int>()
//    val boxInteger: AnimalBoxContra<Int> = AnimalBoxContra<Number>()
//}
