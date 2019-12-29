package com.wxmimperio.scala.collections

import scala.collection.mutable

object ScalaSet {
    def main(args: Array[String]): Unit = {
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
    }
}
