package com.wxmimperio.scala.collections.operation

object ViewOps {
    def main(args: Array[String]): Unit = {
        def reverseEqual(n: Int): Boolean = {
            n.toString.equals(n.toString.reverse)
        }

        val list = (1 to 200).filter(reverseEqual)
        println(list)

        // 使用view时，只有迭代了上一个，下一个才会参与计算，属于lazy
        // 此时不会调用 filter 中的方法
        val viewList = (1 to 200).view.filter(reverseEqual)
        println(viewList)
        for(item <- viewList) {
            println(item)
        }
    }

}
