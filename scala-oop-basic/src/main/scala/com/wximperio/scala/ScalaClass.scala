package com.wximperio.scala

object ScalaClass {
    def main(args: Array[String]): Unit = {
        val person = new Person
        // person.age_$eq(15)
        person.age = 15
        // person.name_$eq("wxm")
        person.name = "wxm"

        // 使用多态时必须声明对象类型
        // 子类的引用交给父类
        val student1: Person = new Student
        println(student1)
        // 子类的类型推导还是子类
        val student = new Student
        student.name = "wxm"
        student.age = 20
        student.school = "学校"

        println(student)
    }
}

class Person {
    // _ 表示默认值，string为空字符串，int为0
    var name: String = _
    var age: Int = _
}

class Student extends Person {
    var school: String = _
}
