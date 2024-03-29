## Functional programming basics

### 函数返回值

> 如果函数明确声明没有返回值`Unit`，则函数中return不起作用

> 如果函数明明确没有返回值或者不确定返回值类型，则用`Any`

> 函数形参可以指定默认值，方法体内可以对默认值进行覆盖`def fun(a:String = "wxm"): String = {}`

> scala 中形参默认是val类型，不能在函数中进行修改

> 返回值为Unit的函数，也叫做`过程`

```scala
// 形式一，返回值为指定类型：
// :返回值 = {} 
def fun(a:Int, b:Int): Int = {
}

// 形式二，返回值为自动类型推导:
// = {}
def fun(a:Int, b:Int) = {
}
// 形式三，无返回值,return 不生效
// {}
def fun(a:Int, b:Int) {
}
```

### 函数的可变参数

> 可变形参是个集合：ArraySeq()

> 可变参数需要写在所有形参的最后，不能提前

```scala
// 从0开始n个参数
def fun4(a: Int*) = {
    println(a)
}

// 从1开始n个参数
def fun5(a:String,b:Int*): String = {

}
```

### Lazy Function

> 将函数的求值尽可能的推迟，直到调用时才真正执行

> 将函数的返回值声明为`lazy`，直到首次使用时才进行执行

> lazy 不能修饰var变量，lazy是线程安全的

> lazy 不仅可以修饰函数，也可以修饰变量`lazy val a = 1`

```scala
lazy val str3 = (a: Int, b: Int) => {
    a + b
}
```

### Scala 隐式转换

> 隐式转换函数以`implicit`关键字声明的，带有`一个`参数的函数；会自动应用将一种类型转换成另一种类型

> 隐式函数要在作用域中才可以生效

> 编译器在编译过程中出错时，会在作用域范围内寻找是否存在可用隐式转换

```scala
implicit def f1(d: Double): Int = {
    d.toInt
}

// 自动应用f1$1(6.5)进行类型转换
val num1: Int = 6.5
println(num1)
```

> 隐式函数的函数名可以任意，只与函数的签名（参数类型、返回值类型）有关系

> 在同一个作用域中隐式函数可以有多个，可以形成一个隐式函数的列表，但同时只能有`唯一一个`可以识别应用

> 可以通过隐式转换动态扩展功能

```scala
// 通过隐式转换在不修改原始代码的情况下，为类扩展新的方法
implicit def getNewAge(a: ExtensionMethodA): ExtensionMethodB = {
    new ExtensionMethodB
}

val a = new ExtensionMethodA
// 实际上是利用getNewAge$1(a)给了一个ExtensionMethodB的实例，然后用ExtensionMethodB的实例调用相应方法
a.getAge()

class ExtensionMethodA {
    def getName(): Unit = {
        println("ExtensionMethodA")
    }
}

class ExtensionMethodB {
    def getAge(): Unit = {
        println("ExtensionMethodB")
    }
}
```

> `隐式变量`某个形参标记为`implicit`，编译器会在函数声明`隐式参数`的情况下，寻找作用域内的`隐式变量`当做函数的隐式参数

> 参数的默认值只能作用在一个参数，而`隐式变量`可以作用在多个参数上

> 当同事有`隐式变量`和普通变量默认值时，implicit优先级更高

> 当一个隐式参数没有匹配到隐式变量时，任然会使用默认值

> 编译器优先级：自定义传值 > 隐式变量值 > 指定的默认值

> 在隐式变量匹配时，相同作用域中不能出现`二义性`的参数，隐式函数也不能存在`嵌套`，即隐式函数中嵌套使用隐式函数，会形成一个无终止的递归调用

```scala
// 隐式变量
implicit val name: String = "hello"
// 隐式参数
def hello(implicit a: String): Unit = {
    println("this is " + a)
}
// 调用时直接hello$1(name)，使用隐式参数的函数时，不用加()
hello
```

> 用`implicit`修饰的类成为`隐式类`

- 1.隐式类构造函数有且只有一个
- 2.隐式类只能定义在`类`、`伴生对象`、`包对象`中，隐式类是不能顶级的
- 3.隐式类不能是`case class`
- 4.作用域中不能有同名其他标志符

> 隐式转换的时机

- 1.当方法中的参数与目标类型不一致时
- 2.当对象调用所在类不存在的方法时，会自动寻找隐式转换

> 隐式转换过程，编译器会在当前作用域下寻找`隐式方法`、`隐式类`、`隐式对象`、`隐式属性`

## Match 模式匹配

> 使用`match`和`case`关键字进行模式匹配，不用写break，`case _`表示匹配的默认值，类似java中switch 的default

> 匹配成功则执行`=>`之后的代码块

> 如果所有case都不匹配，且没有case _，则抛出`MatchError`

> case _ 默认匹配只能写在最后，否则之后的匹配都不会执行

```scala
val op = "*"
val a = 5
val b = 4
op match {
    case "+" => println(a + b)
    case "-" => println(a - b)
    case "*" => println(a * b)
    case "/" => println(a / b)
    case _ => println("miss match")
}
```

> 如果要匹配范围，则需要加`条件守卫`，即可以在case中加入if判断，且`_`此时并不是表示默认匹配

```scala
case _ if op.toInt < 10 && op.toInt > 5 => println("3 < x < 10")
```

> 如果case之后一个变量，则模式匹配会将匹配到的值赋值给这样变量，这样=>后的代码块就可以使用此变量；此时这个case相当于无条件匹配

```scala
case param => println("this is " + param)
```

> 可以直接将整个模式匹配赋值给一个变量，匹配到的代码块最后一句就是模式匹配的返回值，赋值给这个变量

> 模式匹配可以直接进行类型匹配，类似使用`isInstanceOf`,`asInstanceOf`

> 在类型匹配中case _:类型，这个下划线表示`隐藏变量名`，并不表示默认匹配

```scala
// 根据param的类型去匹配
val index = 3
val param = if (index == 0) List(1, 2, 3)
    else if (index == 1) Map("a" -> 1, "b" -> 2)
    else if (index == 2) 123
    else if (index == 3) Array("dfas")
    else "aaa"

param match {
    case a: List[Int] => println("List[Int]=" + a)
    case b: Map[String, Int] => println("Map[String, Int]=" + b)
    case c: BigInt => println("BigInt=" + c)
    case d: Array[String] => println("Array[String]=" + d)
    case _ => println("miss match")
}
```

> `模式匹配-数组`

> Array(0)表示匹配只有一个且为0的元素

> Array(x,y)表示匹配有两个元素的数组，且将值赋值给x，y

> Array(0,_*)表示匹配以0开始的数组

```scala
val arrs = Array(Array(0), Array(5, 6), Array(0, "a", 1.1))
for (arr <- arrs) {
    arr match {
        case Array(0) => println("Array(0)")
        case Array(x, y) => println("Array(x,y), x=" + x + ", y=" + y)
        case Array(0, _*) => println("Array(0,_*)")
        case _ => println("miss match")
    }
}
```

> `模式匹配-列表`

> 0 :: Nil表示0

> x :: y :: Nil表示两个元素

> 0 :: tail表示以0开头，tail返回的是一个新的List

```scala
val arrs = List(List(0), List(5, 6), List(0, "a", 1.1))
for (arr <- arrs) {
    arr match {
        case 0 :: Nil => println("0 :: Nil")
        case x :: y :: Nil => println("x :: y :: Nil" + x + ", y=" + y)
        case 0 :: tail => println("0 :: tail = " + tail)
        case _ => println("miss match")
    }
}
```

> `模式匹配-元组`

> (0,_)匹配以0开头的二元组，并且用_表示的元素忽略

> (y,0)匹配以0为第二个元素的二元组

> (x,y,z)匹配任意三元组

```scala
val arrs = List((0, 1), (10, 0), (1, 2, "wxm"))
for (arr <- arrs) {
    arr match {
        case (0, _) => println("(0,_)")
        case (y, 0) => println("(y, 0), y = " + y)
        case (x, y, z) => println("(x, y, z), x = " + x + ", y = " + y + ", z = " + z)
        case _ => println("miss match")
    }
}
```

> `模式匹配-对象`

> case中对象的`unapply`(对象提取器)方法返回`some`集合，则为匹配成功

> 返回`none`类型则为匹配失败

> case后对象提取器的入参为多个时，默认去调用`unapplySeq`方法，执行后将返回的多个参数赋值给入参；入参与返回参数个数一定要相同才能匹配

> case class样例类，会自动生成apply和unapply方法

```scala
object Person {
    // 1. 对象提取器
    // 2. 返回一个Option的Seq集合
    def unapplySeq(arg: String): Option[Seq[String]] = {
        println("in unapplySeq")
        if (arg.contains(",")) Some(arg.split(","))
        else None
    }
}

val person: String = "1,2,3"
// 此时匹配的是多个参数，返回的是Some(1,2,3)
person match {
    // 会去调用对象的unapplySeq方法，因为入参是多个
    // some返回值的个数，要和构造器入参的个数一致，否则无法匹配
    case Person(a, b, c) => println("a = " + a + ", b = " + b + ", c = " + c)
    case _ => println("miss match")
}

def matchDemo09: Unit = {
    // 样例类，自动生成apply和unapply方法
    case class Student(name: String, age: Int)
    val student = Student("wxm", 19)

    student match {
        case Student("wxm", 19) => println("wxm, 19")
        case _ => println("not match")
    }
}
```

## 偏函数

> 在对符合某个条件，而不是所有条件时执行的函数，即`可以部分适用的函数`

> 将包在大括号内的一组`case`语句封装成一个函数，就叫偏函数

> 偏函数只会作用于指定类型的参数、或指定范围值的参数进行计算；超出范围的直接忽略

> 偏函数需要继承一个`PartialFunction`的特质

> collect函数支持偏函数

```scala
val list = List(1, 2, 3, 4, 5, "fasd", "dfasd")
// 1. 定义一个偏函数，入参是Any类型，返回值是Int类型
// 2. 如果isDefinedAt(x: Any)返回true，则会调用apply方法构建对象实例；如果是false则忽略
// 3. apply(v1: Any) 为具体执行的逻辑
val partialList = new PartialFunction[Any, Int] {
    override def isDefinedAt(x: Any): Boolean = x.isInstanceOf[Int]

    override def apply(v1: Any): Int = {
        v1.asInstanceOf[Int] + 10
    }
}
println(list.collect(partialList))

// 偏函数简写1
def partialFunction01: PartialFunction[Any, Int] = {
    case x: Int => x + 10
}
println(list.collect(partialFunction01))
// 偏函数简写2
println(list.collect({ case x: Int => x + 10 }))
```

## 匿名函数

> 不需要写def关键字

> 不需要指定返回值，自动类型推导作为返回值

> 函数的`=`替换成`=>`

> 如果有多行，可以用`{}`代码块进行包装

```scala
val add = (param: Int) => {
    println(param)
    param + 10
}

println(add(20))
```

## 高阶函数

> 可以接收函数作为参数的函数成为高阶函数；也可以返回函数

> 可接收的函数不止一个，可以为多个

```scala
// 参数为函数
def fun1(f: Double => Double, param: Double): Double = {
    f(param)
}

def fun2(param: Double): Double = {
    param * param
}

val result = fun1(fun2, 2.0)
println(result)


// 多个函数参数
def fun1(f: Double => Double, f1: Double => Int, param: Double): Double = {
    f(f1(param))
}

def fun2(param: Double): Double = {
    param * param
}

def fun3(param: Double): Int = {
    (param * 10).toInt
}

val result = fun1(fun2, fun3, 2.0)
println(result)


// 返回值为函数
def fun1(x: Int) = {
    y: Int => x * y
}

// fun1(2) 整体是 y: Int => x * y 这个匿名函数；即函数柯里化
val result = fun1(2)(4)
println(result)
```

## 参数类型推断

> 参数类型为可推断时，可以省略类型

> 传入的参数只有单个参数时，可以省略括号

> 如果变量只在`=>`右边出现一次，可以简写成`_`

```scala
val list = List(1, 2, 3, 4)
// 用匿名函数
println(list.map((x: Int) => x + 1))
// 类型可以省略
println(list.map((x) => x + 1))
// 括号可以省略
println(list.map(x => x + 1))
// 可以简写_
println(list.map(_ + 1))

// 匿名函数
println(list.reduce((a: Int, b: Int) => a + b))
// 类型可以省略
println(list.reduce((a, b) => a + b))
// 简写_
println(list.reduce(_ + _))
```

## 闭包

> 一个函数与其相关引用环境组成一个整体

> 要清楚闭包函数，引用了函数外的哪些变量

```scala
def fun1(n1: Int) = (n2: Int) => n1 + n2
// fun1(10)是闭包
println(fun1(10)(20))

//等价于
val f1 = fun1(10)
val f2 = f1(20)
println(f2)
```

## 柯里化

> 接收多个参数的函数都可以转换为接收单个参数的函数，这个过程就是柯里化

```scala
def add(x: Int)(y: Int) = x + y
// 函数柯里化
println(add(1)(2))

def eq(str1: String, str2: String): Boolean = {
    str1.equals(str2)
}
// 隐式类，用于扩充string方法
implicit class CheckEq(s: String) {
    // 高阶函数
    def checkEq(ss: String)(f: (String, String) => Boolean): Boolean = {
        f(s.toLowerCase(), ss.toLowerCase())
    }
}
println("abc".checkEq("ABC")(eq))
```

## 抽象控制

> 参数是函数（代码块）

> 代码块在函数中被使用几次，就会调用几次

```scala
def demo01: Unit = {
    def myThread(f1: () => Unit): Unit = {
        new Thread() {
            override def run(): Unit = {
                f1()
            }
        }.start()
    }

    myThread {
        () => {
            println(Thread.currentThread().getName)
        }
    }
}

// 简写
def demo02: Unit = {
    def myThread(f1: => Unit): Unit = {
        new Thread() {
            override def run(): Unit = {
                f1
            }
        }.start()
    }

    myThread {
        println(Thread.currentThread().getName)
    }
}
```