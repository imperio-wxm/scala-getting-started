package com.wxmimperio.scala.collections

import scala.collection.mutable

object ScalaQueue {
    def main(args: Array[String]): Unit = {

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
    }
}
