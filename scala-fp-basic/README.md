## Functional programming basics

- 函数返回值

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

- 函数的可变参数

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

- Lazy Function

> 将函数的求值尽可能的推迟，直到调用时才真正执行

> 将函数的返回值声明为`lazy`，直到首次使用时才进行执行

> lazy 不能修饰var变量，lazy是线程安全的

> lazy 不仅可以修饰函数，也可以修饰变量`lazy val a = 1`

```scala
lazy val str3 = (a: Int, b: Int) => {
    a + b
}
```

