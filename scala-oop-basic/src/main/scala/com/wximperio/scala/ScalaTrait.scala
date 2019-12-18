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

        // 特质字段
        val classTrait = new ClassTrait
        println(classTrait.name)
        val classTrait01 = new ClassTrait01 with Trait003
        println(classTrait01.name)
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

// 特质中重写抽象方法
trait Trait001 {
    def getName(in: Int)
}

trait Trait002 extends Trait001 {
    // 如果我们在子特质中重写了父特质的抽象方法，且同时调用了super父特质的抽象方法
    // 此时子特质并不是完全实现，所以需要声明为：abstract override
    // 此时super方法执行的顺序与动态混入有关，同样是向前面的特质找方法
    abstract override def getName(in: Int): Unit = {
        println("This trait 002..." + in)
        super.getName(in)
    }
}

trait Trait003 {
    val name: String = "test trait field"
}

class ClassTrait extends Trait003 {
}

class ClassTrait01 {
}

// 特质继承类，扩展类功能
trait LogException extends Exception {
    def logException(): Unit = {
        println(getMessage)
    }
}

// MyException 同样也是 Exception的子类，因为LogException继承自Exception
class MyException extends LogException {
    override def getMessage: String = {
        "this is my exception"
    }
}

// 自身类型self type
trait Logger {
    // 定义自身类型，告诉编译器，声明Logger就是Exception
    // 此写法相当于 trait Logger extends Exception {}
    // 要求混入Logger 特质的类也应该是Exception的子类
    this: Exception =>
    def log(): Unit = {
        println(getMessage)
    }
}
