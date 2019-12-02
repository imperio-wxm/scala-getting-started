package com.wximperio.scala

object ScalaClass {
    def main(args: Array[String]): Unit = {
        val person = new Person
        // person.age_$eq(15)
        person.age = 15
        // person.name_$eq("wxm")
        person.name = "wxm"
    }
}

class Person {
    // _ 表示默认值，string为空字符串，int为0
    var name: String = _
    var age: Int = _
}
