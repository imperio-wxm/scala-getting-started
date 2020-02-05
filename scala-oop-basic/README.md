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

### scala trait

> 等价于java中的接口+抽象类，并不是实现而是继承trait，特征中可以有抽象方法，也可以有实体方法

> 多个类具有相同的特征时，利用`trait`关键字将相同特质抽象独立出来

> 在scala中，使用java中的接口，都可以当做trait使用；直接用`extends关键字继承一个trait`

> 如果一个类有`父类`，或有多个特质需要继承，则用`with`关键字

```scala
// 没有父类
class 类名 extends 特质1 with 特质2 with 特质3....
//有父类
class 类名 extends 父类 with 特质1 with 特质2 with 特质3....
```

> trait当做java`接口`使用，此时底层原理沿用java的`implements`机制（可以反编译看字节码文件）

```scala
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
```

> trait中既有抽象方法又有普通实现方法时，底层会生成两种trait字节码文件：Trait.class（对应java中的接口） 和 Trait$class.class（对应java中的抽象类）

```scala
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
```

> 具有`动态混入（mixin）`特性，即当B继承A，且A动态混入了trait C中的某些方法，A并不强制拥有trait C中的方法

> 动态混入可以在不修改原有类声明、定义、继承的基础上，扩展类的功能

> 动态混入用`with`关键字，使用在new对象之后；可以是普通类也可以是抽象类，如果抽象类中已经含有抽象方法，则先with特质再对抽象方法进行匿名实现

```scala
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

trait MyTrait02 {
    def getName(): Unit = {
        println("My trait02....")
    }
}

// 普通类
class G {
}
// 抽象类
abstract class H {
}
// 抽象类，含有抽象方法
abstract class I {
    def hello()
}
```

> 当动态混入多个特质时成为`叠加特质`，叠加特质的`声明从左到右`，但是`执行从右到左`

> 如果调用`super`并不是调用父特质，而是向前找特质，如果找不到才向父特质找

> 在特质中重写抽象方法，需要使用`abstract override`关键字，此时重写的抽象方法任然是抽象的，需要后面的特质继续实现

```scala
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
```

> `富接口`当特质中既有抽象方法又有非抽象方法时，此时特质成为富接口

> 特质中可以有属性字段，当字段初始化了就是具体字段，没初始化就是抽象字段；字段是直接加到混入的类中，并不是继承得到

```scala
 // 特质字段
val classTrait = new ClassTrait
println(classTrait.name)
val classTrait01 = new ClassTrait01 with Trait003
println(classTrait01.name)
        
trait Trait003 {
    val name: String = "test trait field"
}

class ClassTrait extends Trait003 {
}

class ClassTrait01 {
}
```

> 扩展类的特质，即特质可以继承类，用于扩展类的功能

> 所有继承了`扩展类的特质`的类，都是特质继承类的子类

> 如果当前类同时继承了普通的父类，和扩展类的特质，为避免多继承发生；普通的父类一定要是扩展特质类的子类

```scala
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
```

> 自身类型（selfType）：主要为了解决特质的`循环依赖问题`，引入一个`限制混入该特质类的类型`

```scala
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
```

### scala 嵌套类

> 成员内部类和Java基本相同，静态内部类由伴生对象产生

> 成员内部类访问外部类的私有属性`方式一`，通过`OuterClass.this.属性`访问，其中`OuterClass.this.`代表了外部类的对象实例

> 成员内部类访问外部类的私有属性`方式二`，通过`别名.属性`访问，此时`别名 =>` 要放在外部类属性的前面

> 由于内部类关联与外部类的对象，所以需要使用`类型投影`来消除绑定关系：`外部类#内部类`

```scala
// 外部类实例
val outerClassDemo01 = new OuterClassDemo
val outerClassDemo02 = new OuterClassDemo

// 成员内部类，通过外部类实例创建；成员内部类是和外部对象关联的
val innerClass01 = new outerClassDemo01.InnerClass
val innerClass02 = new outerClassDemo02.InnerClass

// 成员内部类访问外部类的属性
innerClass01.getAge()
innerClass01.getName()

// 成员内部类通过别名访问外部类
val outerAlias = new OuterClassDemoAlias
new outerAlias.InnerClass().getAge()

// 通过伴生对象创建静态内部类
val staticInnerClass = new OuterClassDemo.StaticInnerClass

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
```

### 模版类

> 模版类会默认生成很多方法，用`case`关键字声明

> 模版类是为了模式匹配而优化的类

> 模版类中的入参默认都是`val`类型

```scala
abstract class Father

// 模版类
case class Child1(name: String) extends Father

case class Child2(age: Int, gender: String) extends Father

case object Obj1 extends Father

def demo1: Unit = {
    // 模版类会自动生成unapply方法
    for (item <- List(Child1("child1"), Child2(27, "male"), Obj1)) {
        item match {
            case Child1(i) => println("i = " + i)
            case Child2(i, j) => println("i = " + i + ", j = " + j)
            case Obj1 => println("Obj1")
            case _ => println("miss match")
        }
    }
}
```


### 泛型

```scala
object ScalaGeneric {
    def main(args: Array[String]): Unit = {
        val intMsg = new IntMsg[Int](18)
        println(intMsg)

        val strMsg = new StringMsg[String]("string")
        println(strMsg)

        val muMsg = new MultipleMsg[Double, Boolean, Float](10.0D, true, 0.5F)
        println(muMsg.booleanMsg)
        println(muMsg.doubleMsg)
        println(muMsg.floatMsg)
    }
}

abstract class Message[T](str: T) {
    def msg: T = str
}

class IntMsg[Int](value: Int) extends Message(value)

class StringMsg[String](value: String) extends Message(value)

class MultipleMsg[A, B, C](val doubleMsg: A, val booleanMsg: B, val floatMsg: C)
```




