package com.wxmimperio.scala

object ScalaException {
    def main(args: Array[String]): Unit = {
        try {
            var a = 1 / 0
        } catch {
            case ex: ArithmeticException => println(s"捕获了异常: $ex")
            case ex: Exception => println(s"捕获了异常： $ex")
        } finally {
            println("finally！")
            myException()
        }
    }

    def myException(): Nothing = {
        throw new Exception("my exception")
    }
}
