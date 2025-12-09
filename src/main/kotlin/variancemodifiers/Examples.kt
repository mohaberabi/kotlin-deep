//package org.mohaberabi.testideplugin.variancemodifiers
//
//private fun takeIntList(list: List<Int>) {}
//private fun takeIntMutableList(list: MutableList<Int>) {}
//
//private fun takeAnyList(list: List<Any>) {}
//
//class BoxOut<out T>
//
//fun takeBoxOutInt(box: BoxOut<Int>) {}
//
//
//fun takeBoxOutNumber(box: BoxOut<Number>) {}
//
//
//fun takeBoxOutNothing(box: BoxOut<Nothing>) {}
//
//
//fun takeBoxOutStar(box: BoxOut<*>) {}
//
//
//class BoxIn<in T>
//
//fun takeBoxInInt(box: BoxIn<Int>) {}
//
//
//fun testAllGenericVariants() {
//
//    takeIntList(listOf<Any>())
//    takeIntList(listOf<Nothing>())
//
//    takeIntMutableList(mutableListOf<Any>())
//    takeIntMutableList(mutableListOf<Nothing>())
//
//    takeAnyList(listOf<Any>())
//    takeAnyList(mutableListOf<Nothing>())
//
//    takeBoxOutInt(BoxOut<Int>())
//    takeBoxOutInt(BoxOut<Number>())
//    takeBoxOutInt(BoxOut<Nothing>())
//
//    takeBoxOutNumber(BoxOut<Int>())
//    takeBoxOutNumber(BoxOut<Number>())
//    takeBoxOutNumber(BoxOut<Nothing>())
//
//    takeBoxOutNothing(BoxOut<Int>())
//    takeBoxOutNothing(BoxOut<Number>())
//    takeBoxOutNothing(BoxOut<Nothing>())
//
//    takeBoxOutStar(BoxOut<Int>())
//    takeBoxOutStar(BoxOut<Number>())
//    takeBoxOutStar(BoxOut<Nothing>())
//
//    takeBoxInInt(BoxIn<Int>())
//    takeBoxInInt(BoxIn<Number>())
//    takeBoxInInt(BoxIn<Nothing>())
//    takeBoxInInt(BoxIn<Any>())
//}
//
//
