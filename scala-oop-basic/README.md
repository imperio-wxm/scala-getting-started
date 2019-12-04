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

- scala中的构造函数

> scala中有两种构造函数，主构造函数、辅助构造函数

> 主构造函数只能由一个：`类名(形参)：{}`；通过形参的个数、类型不同来构造不同的辅助构造函数，用`def this(形参列表) {}`来定义

> 辅助构造函数的第一句，一定要显式调用主构造函数

> 用`private`关键字表示私有主、辅助构造函数

> 主构造函数的形参没有被修饰符修饰，则此形参为`私有变量`，有效范围就在构造函数内；如果参数使用val，则此形参为类的私有`只读`属性；如果参数使用var，则此形参为类私有的`读写`属性

> @BeanProperty注解，会自动添加java 中getter、setter方法

```scala
// 主构造函数
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
        // 间接调用主构造器
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
```