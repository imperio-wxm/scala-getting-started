package com.wxmimperio.scala.function

object AnonymousFunction {
    def main(args: Array[String]): Unit = {
        val add = (param: Int) => {
            println(param)
            param + 10
        }

        println(add(20))
    }
}
