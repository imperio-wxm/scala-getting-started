package com.wxmimperio.scala.collections

object ScalaTuple {
    def main(args: Array[String]): Unit = {
        // 此声明为Tuple4
        val tuple = (1, 2, 3, 4, "wxm")
        println(tuple)
        // _从1开始
        println(tuple._1)
        // productElement 从0开始
        println(tuple.productElement(0))

        // 遍历
        for (i <- tuple.productIterator) {
            println(i)
        }
    }
}
