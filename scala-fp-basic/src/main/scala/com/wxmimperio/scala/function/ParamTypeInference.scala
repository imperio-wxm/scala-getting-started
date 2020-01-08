package com.wxmimperio.scala.function

class ParamTypeInference {
    def main(args: Array[String]): Unit = {
        val list = List(1, 2, 3, 4)
        // 用匿名函数
        println(list.map((x: Int) => x + 1))
        // 类型可以省略
        println(list.map((x) => x + 1))
        // 括号可以省略
        println(list.map(x => x + 1))
        // 可以简写_
        println(list.map(_ + 1))

        // 匿名函数
        println(list.reduce((a: Int, b: Int) => a + b))
        // 类型可以省略
        println(list.reduce((a, b) => a + b))
        // 简写_
        println(list.reduce(_ + _))
    }
}
