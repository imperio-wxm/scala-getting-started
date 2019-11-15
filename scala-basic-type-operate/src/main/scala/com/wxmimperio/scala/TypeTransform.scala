package com.wxmimperio.scala

object TypeTransform {
    def main(args: Array[String]): Unit = {
        // 低精度的可以向高精度的转换
        // Byte -> Short -> Int(Char 可以转Int) -> Long -> Float -> Double

        // 自动类型转换
        val aa: Long = 123456
        val bb: Float = aa
        println(bb)

        // 强制类型转换
        val cc: Float = 12.3F
        println(cc.toInt)

        // 变字符串
        val dd: Double = 12.56D
        println(dd + "")

        val ee = "3.14"
        println(ee.toDouble.toInt)
    }
}
