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
