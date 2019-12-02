package com.wxmimperio.scala

object ScalaException {
    def main(args: Array[String]): Unit = {
        try {
            throwsMyException()
            var a = 1 / 0
        } catch {
            case ex: ArithmeticException => println(s"捕获了异常: $ex")
            case ex: Exception => println(s"捕获了异常： $ex")
            case ex: NumberFormatException => {
                println("处理NumberFormatException" + ex.getMessage)
            }
        } finally {
            println("finally！")
            myException()
        }
    }

    def myException(): Nothing = {
        throw new Exception("my exception")
    }

    // 等价于java中的 throws NumberFormatException.class
    @throws(classOf[NumberFormatException])
    def throwsMyException() = {
        "abc".toInt
    }
}
