package com.wxmimperio.scala

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
    }
}
