## Object oriented programming basics

- scala中的成员变量

> 成员变量默认都为`private`类型

> 编译后会以变量生成两个`public`方法，变量同名() => getter() 和 变量同名_$eq() => setter()

```scala
class Person {
    // _ 表示默认值，string为空字符串，int为0
    
    // name() name_$eq()
    var name: String = _
    // age() age_$eq()
    var age: Int = _
}
```