package com.wxmimperio.scala.collections

import scala.collection.mutable.ListBuffer

object ScalaList {
    def main(args: Array[String]): Unit = {
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
    }
}
