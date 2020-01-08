package com.wxmimperio.scala.function

object HigherOrderFunction {
    def main(args: Array[String]): Unit = {
        // demo01
        // demo02
        demo03
    }

    def demo01: Unit = {
        def fun1(f: Double => Double, param: Double): Double = {
            f(param)
        }

        def fun2(param: Double): Double = {
            param * param
        }

        val result = fun1(fun2, 2.0)
        println(result)
    }

    def demo02: Unit = {
        def fun1(f: Double => Double, f1: Double => Int, param: Double): Double = {
            f(f1(param))
        }

        def fun2(param: Double): Double = {
            param * param
        }

        def fun3(param: Double): Int = {
            (param * 10).toInt
        }

        val result = fun1(fun2, fun3, 2.0)
        println(result)
    }

    def demo03: Unit = {
        def fun1(x: Int) = {
            y: Int => x * y
        }

        // fun1(2) 整体是 y: Int => x * y 这个匿名函数；即函数柯里化
        val result = fun1(2)(4)
        println(result)
    }
}
