package com.wxmimperio.scala

import com.wxmimperio.scala.CommonMethods.{Common, FatherCommon}

object CommonMethods {
    def main(args: Array[String]): Unit = {
        println(classOf[Common])
        var common = new Common
        // 使用反射机制
        println(common.getClass.getName)

        var fatherCommon = new FatherCommon

        // 自动向上类型转换
        fatherCommon = common
        // 多态，强制向下类型转换
        val childCommon = fatherCommon.asInstanceOf[Common]
        println(childCommon.name)

        // 判断是否是T类型
        println(childCommon.isInstanceOf[Common])
        println(new FatherCommon().isInstanceOf[Common])
    }

    class Common extends FatherCommon {
        val name: String = "wxm"
    }

    class FatherCommon {
        var age: Int = 20
    }

}
