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

### Tuple

> 可以理解为一个容器，可以将无关的元素封装成一个整体

> 元组最多可以放`22`个元素

> Tuple由Tuple1—Tuple22组成，后面的数值表示Tuple中元素的个数，会根据元素个数不同，自动对应不同的元组类型

> 访问元组可以通过下划线+序号（`_1,_2...`）方式访问，也可以通过索引`productElement`访问

> 遍历元组需要迭代器

```scala
// 此声明为Tuple4
val tuple = (1, 2, 3, 4, "wxm")
println(tuple)
// _从1开始
println(tuple._1)
// productElement 从0开始
println(tuple.productElement(0))

// 遍历
for (i <- tuple.productIterator) {
    println(i)
}
```

## List

> scala中List不可变，属于Seq的子类

> 由于list不可变，所以给list追加元素只能返回一个新的list，原本的list不会改变

> 末尾添加 list `:+` item; 首部添加 item `+:` list

> 双冒号`::`表示将左边的元素按照`从右到左`的顺序添加到`Nil`空集合后返回

> 三冒号`:::`表示将左边的每一个元素（如果还是集合要将集合打开，而不是整个集合）按照`从右到左`的顺序添加到`Nil`空集合后返回

> 做冒号操作时，最后一个元组必须是集合

> 三冒号的最后两个元素，即`:::`左右两边的元素必须是集合

```scala
val list = List(1, 2, 3, 4, "wxm")
println(list)
// 空list
val list1 = Nil
println(list1)

println(list(0))

// list 追加会返回一个新的list
val list2 = list :+ 55
println(list2)
val list3 = "wxm" +: list
println(list3)

// 通过::Nil空集合返回
val list4 = "a" :: "b" :: 1 :: 2 :: list :: Nil
println(list4)

// 将list扁平化，每一个元素拿出来
val list5 = 1 :: "wxm123" :: list ::: list4
println(list5)
```

> ListBuffer为可变集合，是Seq —> Buffer 的子类，可以进行增删改查

```scala
// 可变集合ListBuffer
val listBuffer1 = ListBuffer[Any](1, 2, 3, "wxm")
val listBuffer2 = Nil
listBuffer2.appended("fasd")
listBuffer2 + "wxm"
// 将listBuffer2 合并入listBuffer1，listBuffer2不变
listBuffer1 ++= listBuffer2
println(listBuffer1)
println(listBuffer2)
// 合并两个集合返回一个新集合
val listBuffer3 = listBuffer1 ++ listBuffer2
println(listBuffer3)
// 在集合末尾添加
listBuffer3 :+ 5
println(listBuffer3)
// 在集合首部添加
listBuffer3 +: "first"
println(listBuffer3)
// 移除元素
listBuffer3.remove(0)
for (item <- listBuffer3) {
    println(item)
}
```

## Queue

```scala
val queue = new mutable.Queue[Any]
queue += 40
queue.addOne(50)
// 将list打散
queue ++= List(4, 5, 6)

println(queue)

// 出入队列
// 默认从队首出队
val first = queue.dequeue()
println(first)
// 默认从队尾入队
val last = queue.enqueue(111, 222)
println(last)

// 查看元素，不对原队列造成影响，只是查看数据
// 查看首元素
println(queue.head)
// 查看尾元素
println(queue.last)
// 查看除了第一个元素外的其他元素
println(queue.tail)
println(queue.tail.tail.tail)
```

### Map

> 不可变Map，属于有序Map，输出顺序与定义顺序一致，Map中的元素都是Tuple2类型

> 可变Map是无序的

```scala
// 不可变Map
val map01 = Map(123 -> 456, 456 -> "wxm", "dfasdf" -> "fasdf123")
println(map01)

// 可变Map
val map02 = mutable.Map("test" -> 123, 123 -> 456, "abc" -> 777)
println(map02)

// 空map
val emptyMap = new mutable.HashMap[String, String]
println(emptyMap)

// 对偶元组创建map
val tupleMap = mutable.Map((1, 2), ("fasdf", "fasdf"), (123, "sdfasd"))
println(tupleMap)

// map取出元素，如果元素不存在则抛出异常
println(tupleMap(1))
// 判断key是否存在
if (tupleMap.contains(123)) {
    println(tupleMap(123))
} else {
    println("not exist")
}

// get返回值用Some包装，有值则返回包含值得Some，否则返回None
println(tupleMap.get(123).get)
println(tupleMap.get(566))

// get如果有key则返回值，没有key则返回指定的默认值
println(tupleMap.getOrElse(123, "dafsd"))
println(tupleMap.getOrElse(444, "not exist"))

// map的修改
tupleMap("456") = 555
tupleMap(123) = 888
tupleMap.addAll(Map("a" -> 1, "b" -> 2))
println(tupleMap)

// map删除，key不存在不会保存
tupleMap.remove(123)
tupleMap.remove("iii")
println(tupleMap)

// map 遍历
for ((key, v) <- tupleMap) {
    println(key + " _ " + v)
}
println()
for (value <- tupleMap.values) {
    println(value)
}
println()
for (key <- tupleMap.keys) {
    println(key)
}
println()
// 类型是Tuple2
for (tuple <- tupleMap) {
    println(tuple)
    tuple._1
    tuple._2
}
```

### Set

```scala
// 不可变set
val set01 = Set("dfasdf", 1, 1, 456)
println(set01)

// 可变set
val set02 = mutable.Set("fasdfa", "a", "b", "a", 1)
println(set02)
// 添加
set02.add("a")
set02.add("fasdf")
// 删除，不存在也不会报错
set02.remove("a")
// 遍历
for (item <- set02) {
    println(item)
}
```