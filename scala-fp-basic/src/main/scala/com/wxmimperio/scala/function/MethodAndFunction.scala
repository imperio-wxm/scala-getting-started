package com.wxmimperio.scala.function

/**
  * 方法与函数：
  * 是在不同编程范式下的不同称呼
  * OOP则称方法，FP则称函数
  */
object MethodAndFunction {
    def main(args: Array[String]): Unit = {
        // 方法
        val method = new MyMethod
        println(method.sum(1, 2))

        // 方法转函数
        val fun = method.sum _
        println(fun)
        println(fun(4, 5))

        // 函数，函数是一等公民，类似变量，可以直接赋值给一个变量
        // 函数的创建不依赖于类和对象
        val fun1 = (a: Int, b: Int) => {
            a * b
        }
        println(fun1(5, 6))

        // 函数默认值
        def fun2(str: String = "wxm"): String = {
            str
        }

        println(fun2())
        println(fun2("wxm_01"))

        // 带名参数
        def fun3(a: String = "wxm_", b: Int = 1): String = {
            a + b
        }
        // 直接用形参名称指定为哪个参数赋值
        println(fun3(b = 2))

        // 可变参数
        // 可变形参是个集合：ArraySeq
        def fun4(a: Int*) = {
            println(a)
            for (i <- a) {
                println("wxm_" + i)
            }
        }

        fun4()
        fun4(1)
        fun4(2, 3)
    }
}

class MyMethod {
    def sum(a: Int, b: Int): Int = {
        a + b
    }
}
