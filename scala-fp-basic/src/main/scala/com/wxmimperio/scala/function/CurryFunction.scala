package com.wxmimperio.scala.function

object CurryFunction {
    def main(args: Array[String]): Unit = {
        def add(x: Int)(y: Int) = x + y
        // 函数柯里化
        println(add(1)(2))

        def eq(str1: String, str2: String): Boolean = {
            str1.equals(str2)
        }
        // 隐式类，用于扩充string方法
        implicit class CheckEq(s: String) {
            // 高阶函数
            def checkEq(ss: String)(f: (String, String) => Boolean): Boolean = {
                f(s.toLowerCase(), ss.toLowerCase())
            }
        }
        println("abc".checkEq("ABC")(eq))
    }
}
