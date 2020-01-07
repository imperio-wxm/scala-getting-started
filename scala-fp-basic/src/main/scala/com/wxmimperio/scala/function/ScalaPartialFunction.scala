package com.wxmimperio.scala.function

object ScalaPartialFunction {
    def main(args: Array[String]): Unit = {

        val list = List(1, 2, 3, 4, 5, "fasd", "dfasd")
        // 1. 定义一个偏函数，入参是Any类型，返回值是Int类型
        // 2. 如果isDefinedAt(x: Any)返回true，则会调用apply方法构建对象实例；如果是false则忽略
        // 3. apply(v1: Any) 为具体执行的逻辑
        val partialList = new PartialFunction[Any, Int] {
            override def isDefinedAt(x: Any): Boolean = x.isInstanceOf[Int]

            override def apply(v1: Any): Int = {
                v1.asInstanceOf[Int] + 10
            }
        }
        println(list.collect(partialList))

        // 偏函数简写1
        def partialFunction01: PartialFunction[Any, Int] = {
            case x: Int => x + 10
        }
        println(list.collect(partialFunction01))
        // 偏函数简写2
        println(list.collect({ case x: Int => x + 10 }))
    }
}
