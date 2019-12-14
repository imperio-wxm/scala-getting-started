package com.wximperio.scala

object ScalaExtends {
    def main(args: Array[String]): Unit = {
        val student = new Student
        student.sayHi()

        // 调用覆写的抽象属性
        val subChild = new SunChild
        println(subChild.name)
    }

    class Person {
        val name: String = "wxm"

        def sayHi(): Unit = {
            println(s"$name")
        }
    }

    class Student extends Person {
        override def sayHi(): Unit = {
            println("重写父类方法")
            // 调用父类方法用super
            super.sayHi()
        }
    }

    abstract class Child {
        var name: String
        // 初始化之后是一个mutable variable
        var age: Int = 10
    }

    class SunChild extends Child {
        // 1. 如果在子类中重写父类的抽象属性，本质上是重写了此属性的抽象方法
        // 2. override关键字可以省略
        override var name: String = "wxm override"

        // mutable variable cannot be overridden
        // override var age: Int = 20
    }

}
