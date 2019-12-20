package com.wximperio.scala


object ScalaNestedClass {
    def main(args: Array[String]): Unit = {

        // 外部类实例
        val outerClassDemo01 = new OuterClassDemo
        val outerClassDemo02 = new OuterClassDemo

        // 成员内部类，通过外部类实例创建；成员内部类是和外部对象关联的
        val innerClass01 = new outerClassDemo01.InnerClass
        val innerClass02 = new outerClassDemo02.InnerClass

        // 成员内部类访问外部类的属性
        innerClass01.getAge()
        innerClass01.getName()

        // 通过类型投影解决内部类与外部类对象的绑定
        innerClass01.typeProjection(innerClass01)
        innerClass01.typeProjection(innerClass02)

        // 成员内部类通过别名访问外部类
        val outerAlias = new OuterClassDemoAlias
        new outerAlias.InnerClass().getAge()

        // 通过伴生对象创建静态内部类
        val staticInnerClass = new OuterClassDemo.StaticInnerClass
    }
}

// 外部类
class OuterClassDemo {
    val name: String = "wxm"
    private val age: Int = 10

    // 成员内部类
    class InnerClass {
        def getName(): Unit = {
            println(OuterClassDemo.this.name)
        }

        def getAge(): Unit = {
            println(OuterClassDemo.this.age)
        }

        // 创建类型投影，告诉方法在接收参数的时候，不用绑定外部类的对象，只用传相同类型就可接收
        def typeProjection(inner: OuterClassDemo#InnerClass): Unit = {
            println(inner.getName())
        }
    }

}

class OuterClassDemoAlias {
    // 外部类实例的一个别名
    outerAlias =>

    // 成员内部类
    class InnerClass {
        def getName(): Unit = {
            // 通过别名访问
            println(outerAlias.name)
        }

        def getAge(): Unit = {
            println(outerAlias.age)
        }
    }

    // 应用别名时，属性要在别名之后
    val name: String = "alias"
    private val age: Int = 100
}

// 伴生对象
object OuterClassDemo {

    // 使用伴生对象实现静态内部类
    class StaticInnerClass {
    }

}