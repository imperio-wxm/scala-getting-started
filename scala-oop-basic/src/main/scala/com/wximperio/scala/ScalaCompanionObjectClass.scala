package com.wximperio.scala

object ScalaCompanionObjectClass {
    def main(args: Array[String]): Unit = {
        val myTest = new MyTest
        myTest.sayHello()

        println()

        println(ObjApply("apply").name)
    }

    class MyTest {
        def sayHello(): Unit = {
            println("hello")
            // 在伴生类中调用伴生对象的方法，类似于调用静态方法
            MyTest.sayHi()
            // 伴生类也可以访问伴生对象的私有变量
            MyTest.a = "rename"
            println(MyTest.a)
        }
    }

    object MyTest {
        var a: String = "companion object"

        def sayHi(): Unit = {
            println(a)
        }
    }

    class ObjApply {
        var name: String = _

        def this(inName: String) {
            this
            this.name = inName
        }
    }

    // 可以直接使用ObjApply("")创建对象，默认会调用apply方法
    object ObjApply {
        def apply(inName: String): ObjApply = {
            println("apply.....")
            new ObjApply(inName)
        }
    }

}
