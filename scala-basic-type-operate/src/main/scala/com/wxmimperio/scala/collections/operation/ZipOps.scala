package com.wxmimperio.scala.collections.operation

object ZipOps {
    def main(args: Array[String]): Unit = {
        val list1 = List(1, 2, "3")
        val list2 = List(4, 5, "6")
        val zipList = list1.zip(list2)
        println(zipList)

        for (z <- zipList) {
            println(z._1 + ", " + z._2)
        }
    }
}
