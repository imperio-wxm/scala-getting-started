## Object oriented programming basics

### scala中的成员变量

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

### scala中的构造函数

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

### scala中的package

> 子包可以直接访问父包内容，用package的{}提现作用域；而java要引用父包的内容必须import

> scala中的包可以互相嵌套

> 若本包与父包中的内容重名，则用就近原则引用；此时若要引用父包的内容，则需要指定路径

> 父类要引用子包的内容时必须`import`

> `@_root_.xxx`表示绝对路径引入

> package obj 是为了解决直接在包中定义方法、变量

```scala
// 1. 声明一个包对象
// 2. 它是com.wximperio.scala的包对象
// 3. 每一个包只能有一个包对象，且只能在父包中定义，包对象和包是平级关系
// 4. 包对象的名称必须与对应的包一直
// 5. 在包对象中可以定义变量和方法
// 6. 在此包中可以直接使用包对象中的变量和方法
// 7. 包对象会生成两个class，package.class 和 package$.class
// 8. 包对象的变量、函数都会生成在package.class 和 package$.class文件中，调用时是去找这两个class

package object scala {
    var a: String = _

    def test(): Unit = {
        a = "wxm"
        println(a)
    }
}

package scala {

    object ScalaPackageObj {
        def main(args: Array[String]): Unit = {
            // 使用包对象的方法，变量
            scala.a
            test()
        }
    }
}
```

### scala extends & abstract

> 重写非抽象方法必须用`override`关键字

> 调用父类方法用`super`关键字

```scala
class Person {
    val name: String = "wxm"

    def sayHi(): Unit = {
        println(s"$name")
    }
}

class Student extends Person {
    override def sayHi(): Unit = {
        println("重写父类方法")
        // 调用父类方法用super
        super.sayHi()
    }
}
```

> var只能重写一个`抽象`的var属性(声明未初始化的属性就是抽象属性)

> 抽象属性在编译过程中不会声明，但是会自动生成两个抽象方法，所以必须在抽象类中

```scala
abstract class Child {
    var name: String
    // 初始化之后是一个mutable variable
    var age: Int = 10
}

class SunChild extends Child {
    // 1. 如果在子类中重写父类的抽象属性，本质上是重写了此属性的抽象方法
    // 2. override关键字可以省略
    override var name: String = "wxm override"

    // mutable variable cannot be overridden
    // override var age: Int = 20
}
```

> 抽象类无法被实例化

> 抽象类中可以没有抽象方法，抽象方法前`不加abstract`关键字

> 在抽象类中可以有实现的正常方法

> 如果一个子类继承了抽象类，则必须实现这个抽象类中的所有抽象方法和抽象属性

> 抽象方法和属性不能由`private、final`关键字来修饰


### 伴生类和伴生对象

> 在scala中，没有静态的概念，创建一个单例对象来为程序的执行提供入口点

> 当有一个与单例(singleton)对象同名的类时，它被称为伴生(companion)类，单例(singleton)对象调用伴生对象

> 伴生对象用于提供`静态`的支持，其中的属性和方法可以当做static看待，通过伴生对象名.直接使用；伴生类用于提供`非静态`的支持

> 伴生对象通常会使用`apply`数定义伴生类的构造方法，所以伴生对象无法用`new`关键字，而伴生类可以new

> 伴生类及其伴随对象必须在同一个源文件中定义

> 伴生对象和伴生类可以互相访问其私有成员，不与伴生类同名的单例对象称为孤立对象

> 伴生对象的静态实现由`public static final MODULE$`实现，`MODULE$=this`表示的是当前类的一个实例引用

```scala
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
```

> 在伴生对象中定义`apply()`方法，则可以实现`类名()`的方式创建对象实例

```scala
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
```
