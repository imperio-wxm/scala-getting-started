package main.scala

object ScalaType {

    def main(args: Array[String]): Unit = {
        // 定义byte要指定类型
        val testByte: Byte = 7
        println(testByte)

        // 定义int
        val testInt1 = 10
        println(testInt1)
        val testInt2: Int = 100
        println(testInt2)

        // 定义long
        val testLong1 = 100L
        println(testLong1)
        val testLong2: Long = 10
        println(testLong2)

        // scala默认浮点为double，也可以val test = 3.14D
        val testDouble = 3.1415
        println(testDouble)

        // 强制为float
        val testFloat = 4.56F
        println(testFloat)

        // 科学计数，e2 表示乘以10的2次方；e-2表示除以10的2次方
        val testNumber1 = 4.15e2
        println(testNumber1)
        val testNumber2 = 450e-2
        println(testNumber2)

        // 字符类型
        val char1 = 'A'
        println(char1)
        val char2: Char = 100
        println(char2)

        // 字符串
        val str1 = "test"
        println(str1)
        val str2 = """test1"""
        println(str2)

        // Unit
        val unit1 = ()
        println(unit1)
        val unit2: Unit = ()
        println(unit2)

        // Boolean
        val b1 = true
        println(b1)
        val b2: Boolean = false
        println(b2)

        // Any
        val any: Any = "sdfa"
        println(any)
        // 引用类型
        val any1: AnyRef = null
        println(any1)
        // 数值类型超类，可接受任意数值类型
        val numAny: AnyVal = 10.23
        println(numAny)

        // null
        val null1: Null = null
        println(null1)

        // map
        val map1 = Map("key" -> "value")
        println(map1)
        println(map1.get("key"))
        println(map1.get("key1"))
    }
}
