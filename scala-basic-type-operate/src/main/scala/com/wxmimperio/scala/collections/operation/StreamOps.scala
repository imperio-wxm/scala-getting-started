package com.wxmimperio.scala.collections.operation

object StreamOps {
    def main(args: Array[String]): Unit = {
        // "Use LazyList instead of Stream", "2.13.0"
        def getNumber(n: BigInt): LazyList[BigInt] = {
            n #:: getNumber(n + 1)
        }

        val getNumberStream = getNumber(1)
        println(getNumberStream)
        println(getNumberStream.head)
        // 使用tail时会产生新的数据
        println(getNumberStream.tail)
        println(getNumberStream)
    }

}
