package com.wxmimperio.scala.collections

import java.util

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, JavaConversions}
import scala.jdk.CollectionConverters

object ScalaArray {
    def main(args: Array[String]): Unit = {
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

        // 变长数组
        val arrayBuffer = ArrayBuffer[Any](1, 2, 3, 4, 5, "wxm6")
        arrayBuffer.addOne(1)
        arrayBuffer.addAll(ArrayBuffer(41, 23.4))
        arrayBuffer.remove(0)
        arrayBuffer.update(1, "wxm10")
        arrayBuffer.foreach(i => {
            println(i)
        })

        // ArrayBuffer -> Array
        val newArray = arrayBuffer.toArray
        println(newArray.toIndexedSeq)
        // Array -> ArrayBuffer
        val newArrayBuffer = array.toBuffer
        println(newArrayBuffer)

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

        // ArrayBuffer 转成 java list
        println(CollectionConverters.BufferHasAsJava(arrayBuffer).asJava)
        val list = new util.ArrayList[Any]()
        list.add(10)
        list.add(20)
        val list2Buffer = CollectionConverters.ListHasAsScala(list).asScala
        list2Buffer.addOne(30)
        println(list2Buffer)

    }
}
