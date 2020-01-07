package com.wximperio.scala

object ScalaCaseClass {

    abstract class Father

    // 模版类
    case class Child1(name: String) extends Father

    case class Child2(age: Int, gender: String) extends Father

    case object Obj1 extends Father

    def demo1: Unit = {
        // 模版类会自动生成unapply方法
        for (item <- List(Child1("child1"), Child2(27, "male"), Obj1)) {
            item match {
                case Child1(i) => println("i = " + i)
                case Child2(i, j) => println("i = " + i + ", j = " + j)
                case Obj1 => println("Obj1")
                case _ => println("miss match")
            }
        }
    }

    def main(args: Array[String]): Unit = {
        demo1
    }

}
