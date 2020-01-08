package com.wxmimperio.scala.function

object Closure {
    def main(args: Array[String]): Unit = {
        demo01
    }

    def demo01: Unit = {
        def fun1(n1: Int) = (n2: Int) => n1 + n2
        // fun1(10)是闭包
        println(fun1(10)(20))

        //等价于
        val f1 = fun1(10)
        val f2 = f1(20)
        println(f2)
    }
}
