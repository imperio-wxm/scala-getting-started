package com.wxmimperio.scala.matching

object ScalaPatternMatching {
    def main(args: Array[String]): Unit = {

        implicit def f1(d: Double): Int = {
            d.toInt
        }

        val num1: Int = 6.5
        println(num1)

        // 通过隐式转换在不修改原始代码的情况下，为类扩展新的方法
        implicit def getNewAge(a: ExtensionMethodA): ExtensionMethodB = {
            new ExtensionMethodB
        }

        val a = new ExtensionMethodA
        // 实际上是利用getNewAge$(a)给了一个ExtensionMethodB的实例，然后用ExtensionMethodB的实例调用相应方法
        a.getAge()

        // 隐式变量
        implicit val name: String = "hello"
        // 隐式参数
        def hello(implicit a: String): Unit = {
            println("this is " + a)
        }
        // 调用时直接hello$1(name)，使用隐式参数的函数时，不用加()
        hello
    }
}

class ExtensionMethodA {
    def getName(): Unit = {
        println("ExtensionMethodA")
    }

}

class ExtensionMethodB {
    def getAge(): Unit = {
        println("ExtensionMethodB")
    }
}
