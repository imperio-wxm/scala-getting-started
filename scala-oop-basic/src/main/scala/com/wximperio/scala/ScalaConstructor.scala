package com.wximperio.scala

object ScalaConstructor {
    def main(args: Array[String]): Unit = {
        val person = new PersonTest01("wxm", 50)
        println(person)
    }
}

// 类名(形参)：主构造函数只能有一个
class PersonTest01(nameArg: String, ageArg: Int) {
    var name: String = nameArg
    var age: Int = ageArg

    // 通过this来定义辅助构造函数，通过形参的个数、类型不同来构造不同的辅助构造函数
    /*def this(name: String) {

    }*/
    override def toString: String = {
        s"name = $name, age = $age"
    }
}