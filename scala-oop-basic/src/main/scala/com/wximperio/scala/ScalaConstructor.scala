package com.wximperio.scala

import scala.beans.BeanProperty

object ScalaConstructor {
    def main(args: Array[String]): Unit = {
        val person = new PersonTest01("wxm", 50)
        println(person)

        val person01 = new PersonTest01("wxm")
        println(person01)
    }
}

// 类名(形参)：主构造函数只能有一个
class PersonTest01(nameArg: String, ageArg: Int) {
    var name: String = nameArg
    var age: Int = ageArg

    // 通过this来定义辅助构造函数，通过形参的个数、类型不同来构造不同的辅助构造函数
    def this(name: String) {
        // 调用主构造函数
        this(name, 20)
        this.name = name
    }

    def this() {
        this("test")
    }

    override def toString: String = {
        s"name = $name, age = $age"
    }
}

// 将构造函数私有化
class PersonTest02 private(name: String) {
    var n: String = _

    private def this() {
        this("wxm")
    }
}

// name 为私有变量，有效范围就在主构造函数内
// age 为PersonTest03类的只读私有属性
// gender 为为PersonTest03类的读写私有属性
class PersonTest03(name: String, val age: Int, var gender: String) {
    var n: String = _
}

//  @BeanProperty注解，会自动添加java 中getter、setter方法
class PersonTest04 {
    @BeanProperty var name: String = _
}