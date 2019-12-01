## Loop operate

- to and until

> to 关键字表示了一个前后都闭合的区间[]

> until 关键字表示了一个前闭后开的区间[）

```scala
// 1-10
for(i <- 1 to 10) {}
// 1-9
for(i <- 1 until 10) {}
```

- for guard

> 保护式为true则进入循环体，否则跳过当前次循环，类似java中的continue

```scala
 for (i <- 1 to 10 if i != 2) {
    // 不会输出2_wxm
    println(s"$i" + "_wxm")
}
```

- for return

> 将循环的结果返回成一个Vector的集合中（也可以是一个表达式），用yield关键字

```scala
val result1 = for (i <- 1 to 3) yield {
    val sum = i + i
    println(sum)
    // 总是返回代码块的最后一行当做返回值
    sum
}
```

- breakable{} and break()

> 通过break()函数实现循环终止，实际上是抛出了scala.util.control.BreakControl异常

> 通过 breakable{} 高阶函数进行异常捕获，以便代码能继续运行

```scala
// break() 源码:
def break(): Nothing = { throw breakException }

// breakable{} 源码:
def breakable(op: => Unit): Unit = {
    try {
      op
    } catch {
      case ex: BreakControl =>
        if (ex ne breakException) throw ex
    }
}

// 实例
breakable {
    for (i <- 1 to 10) {
        println(i)
        if (i == 5) {
            break()
        }
    }
}
```