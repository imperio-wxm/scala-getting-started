package com.wxmimperio.scala.function

object LazyFunction {
    def main(args: Array[String]): Unit = {
        // str2 会直接执行fun1
        val str2 = fun1("111", "222")
        // str1 不会直接执行fun1
        // lazy 不能修饰var变量，lazy是线程安全的
        lazy val str1 = fun1("wxm_", "test")
        println("=======")
        // 此时才会执行fun1
        println(str1)

        lazy val str3 = (a: Int, b: Int) => {
            a + b
        }
        println(str3(1, 3))

        lazy val a = 1
    }

    def fun1(a: String, b: String): String = {
        println("fun1 start....")
        a + b
    }
}
