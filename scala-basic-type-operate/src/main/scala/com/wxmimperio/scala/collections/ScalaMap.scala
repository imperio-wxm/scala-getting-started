package com.wxmimperio.scala.collections

import scala.collection.mutable

object ScalaMap {
    def main(args: Array[String]): Unit = {
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
    }
}
