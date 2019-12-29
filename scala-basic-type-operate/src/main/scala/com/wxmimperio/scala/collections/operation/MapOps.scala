package com.wxmimperio.scala.collections.operation


object MapOps {
    def main(args: Array[String]): Unit = {
        val list = List(1, 2, 3)
        println(list.map(add).map(involution))

        println(MyList().map(add))

        // flatMap
        val flatList = List("sfasdf", "fasd", "adfadfsdsf")
        println(flatList.flatMap(upper))

        // filter
        println(flatList.filter(filter01))

        // reduceLeft，reduceRight运算方向相反
        // 1 * 2 * 3
        println(list.reduceLeft(involution))

        // fold
        println(list.fold(10)(involution))
        println(list.foldRight(10)(involution))
    }

    def filter01(str: String): Boolean = {
        str.startsWith("a")
    }

    def add(a: Int): Int = {
        a + 1
    }

    def involution(a: Int): Int = {
        a * a
    }

    def involution(a: Int, b: Int): Int = {
        a * b
    }

    def upper(str: String): String = {
        str.toUpperCase
    }
}

// 模仿高阶函数实现map操作
class MyList {
    val list: List[Int] = List(1, 2, 3)
    var newList: List[Int] = List[Int]()

    // 能接收函数作为参数的函数称为高阶函数
    def map(fun: Int => Int): List[Int] = {
        for (item <- list) {
            newList = newList :+ fun(item)
        }
        newList
    }
}

object MyList {
    def apply(): MyList = new MyList()
}
