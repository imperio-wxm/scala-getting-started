package com.wxmimperio.scala

import util.control.Breaks._

object LoopOps {
    def main(args: Array[String]): Unit = {
        // 1 到 10，前后都是闭区间
        for (i <- 1 to 10) {
            println(s"$i" + "_wxm")
        }

        println()

        // 遍历集合
        val list = List("wxm", 1, 1.2F)
        for (item <- list) {
            println(item)
        }

        println()

        // until 前闭后开遍历
        for (i <- 2 until 20) {
            println(s"$i" + "_wxm")
        }

        // 循环保护式
        // 保护式为true则进入循环体，否则跳过当前次循环，类似java中的continue
        for (i <- 1 to 10 if i != 2) {
            // 不会输出2_wxm
            println(s"$i" + "_wxm")
        }

        // 引入变量
        for (i <- 1 to 3; j = i - 1) {
            println(s"$i" + s"-$j")
        }

        println("======")

        // 嵌套循环简洁写法
        for (i <- 1 to 3; j <- 3 to 5) {
            println(s"$i-$j")
        }

        // 循环返回值
        // 将循环的结果返回成一个Vector的集合中，用yield关键字
        // i 可以是一个代码块，可以对i进行逻辑处理
        val result = for (i <- 1 to 3) yield i
        println(result)

        val result1 = for (i <- 1 to 3) yield {
            val sum = i + i
            println(sum)
            // 总是返回代码块的最后一行当做返回值
            sum
        }
        println(result1)

        // for小括号换成大括号
        for {
            i <- 1 to 3
        } {
            println(s"$i" + "_wxm")
        }

        println("======")

        // 步长for循环
        for (i <- Range(1, 10, 2)) {
            println(s"$i" + "_wxm")
        }

        println("======")

        // while
        // while整个结束没有返回值，是Unit类型
        var a = 1
        while (a < 5) {
            println(a)
            a += 1
        }

        println("======")

        // do while
        var b = 1
        do {
            println(b)
            b += 1
        } while (b < 3)

        println("======")

        // 循环终止
        // breakable:
        // 1. 是一个高阶函数
        // 2. 对break()抛出的异常进行处理，代码可以继续执行
        // 3. 当我们传入的是代码块时，用大括号替换小括号
        breakable {
            // 会抛出一个scala.util.control.BreakControl异常
            // def break(): Nothing = { throw breakException }
            for (i <- 1 to 10) {
                println(i)
                if (i == 5) {
                    break()
                }
            }
        }
    }
}
