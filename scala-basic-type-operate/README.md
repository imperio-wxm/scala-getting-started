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

### Scala Exception

> try {} cache {} finally {} 形式

> 只有一个cache语句，其中用`case`模式匹配处理不同的异常类型

> `=>`后可以是一个代码块，用于处理异常

```scala
try {
    var a = 1 / 0
} catch {
    case ex: ArithmeticException => println(s"捕获了异常: $ex")
    case ex: Exception => println(s"捕获了异常： $ex")
} finally {
    println("finally！")
}
```

- throw 表达式是有类型的，类型为Nothing，因为Nothing是所有类型的子类型

```scala
def myException(): Nothing = {
    throw new Exception("my exception")
}
```

- 可以用`throws`注解来声明异常，表示此处可能抛出此类型异常

```scala
@throws(classOf[NumberFormatException])
def throwsMyException() = {
    "abc".toInt
}
```

### scala common methods

- ClassOf

> 用于获取类名

```scala
classOf[String]

// 使用反射机制
println(common.getClass.getName)
```

- asInstanceOf

> 强制类型转换

```scala
val childCommon = fatherCommon.asInstanceOf[Common]
```

- isInstanceOf

> 判断是否是`T`类型

```scala
new FatherCommon().isInstanceOf[Common]
```

## Scala Collections

### Array & ArrayBuffer

> 定长数组，少于指定size会被填充默认值，可以指定array泛型,[Aay]则表示可以存放任何类型

```scala
// 方式一，不足size 长度的用0补充
val array = new Array[Int](20)

for (i <- 0 to 19) {
    array(i) = i
}
for (i <- array) {
    println(i)
}

// 方式二，直接初始化；使用apply初始化；会根据初始化值自动推导数据类型
val array01 = Array(1, 2, "wxm3")
for (i <- array01) {
    println(i)
}

for (index <- 0 until array01.length) {
    println(array01(index) + "_" + index)
}
```

> 变长数组，支持数组的扩容、缩容、更新等操作

> 进行变长操作时，底层是新建一个数组将原来内容copy过去，所以每次hashcode值不同，类似java中的ArrayList

```scala
// 变长数组
val arrayBuffer = ArrayBuffer[Any](1, 2, 3, 4, 5, "wxm6")
arrayBuffer.addOne(1)
arrayBuffer.addAll(ArrayBuffer(41, 23.4))
arrayBuffer.remove(0)
arrayBuffer.update(1, "wxm10")
arrayBuffer.foreach(i => {
    println(i)
})
```

> Array 和 ArrayBuffer的相互转换

```scala
// ArrayBuffer -> Array
val newArray = arrayBuffer.toArray
println(newArray.toIndexedSeq)
// Array -> ArrayBuffer
val newArrayBuffer = array.toBuffer
println(newArrayBuffer)
```

> 多维数组

```scala
// 多为数组的使用
val dim = Array.ofDim[Any](3, 4)
var index = 0
for (i <- dim.indices) {
    for (j <- dim(i).indices) {
        dim(i)(j) = index
        index += 1
    }
}
for (i <- dim) {
    for (j <- i) {
        print(j + "\t")
    }
    println()
}
```

> ArrayBuffer 和 JavaList 互转

```scala
// ArrayBuffer 转成 java list
println(CollectionConverters.BufferHasAsJava(arrayBuffer).asJava)

// java list 转成 ArrayBuffer
val list = new util.ArrayList[Any]()
list.add(10)
list.add(20)
val list2Buffer = CollectionConverters.ListHasAsScala(list).asScala
list2Buffer.addOne(30)
println(list2Buffer)
```