package com.wxmimperio.scala.collections.operation

object IteratorOps {
    def main(args: Array[String]): Unit = {
        val list = List(1, 2, 3, 4, 5, 6, 7, "8").iterator

        while (list.hasNext) {
            println(list.next())
        }

        val list2 = List(1, 2, 3, 4, 5, 6, 7, "8").iterator

        for (item <- list2) {
            println(item)
        }
    }
}
