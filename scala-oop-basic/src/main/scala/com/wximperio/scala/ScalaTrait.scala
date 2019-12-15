package com.wximperio.scala

object ScalaTrait {
    def main(args: Array[String]): Unit = {
        val b = new B
        b.getName()

        val e = new E
        e.getName()

        // 即有抽象方法又有实现方法
        val f = new F
        f.getName()
        f.getAge()

        // 动态混入普通类
        val g = new G with MyTrait02
        g.getName()
        // 动态混入抽象类
        val h = new H with MyTrait02
        h.getName()
        // 动态混入有抽象方法的抽象类
        val i = new I with MyTrait02 {
            override def hello(): Unit = {
                println("hello")
            }
        }
        i.hello()
        i.getName()
    }
}

trait MyTrait {
    // 定义一个抽象方法
    def getName()
}

class A {

}

class B extends A with MyTrait {
    override def getName(): Unit = {
        println("this is B")
    }
}

class D {

}

class E extends D with MyTrait {
    override def getName(): Unit = {
        println("this is E")
    }
}

trait MyTrait01 {
    def getName()

    def getAge(): Unit = {
        println("age = 10")
    }
}

class F extends MyTrait01 {
    override def getName(): Unit = {
        println("this is F")
    }
}

trait MyTrait02 {
    def getName(): Unit = {
        println("My trait02....")
    }
}

class G {

}

abstract class H {

}

abstract class I {
    def hello()
}
