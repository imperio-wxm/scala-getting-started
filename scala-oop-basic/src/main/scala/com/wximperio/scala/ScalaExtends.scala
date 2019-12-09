package com.wximperio.scala

object ScalaExtends {
    def main(args: Array[String]): Unit = {
        val student = new Student
        student.sayHi()
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

}
