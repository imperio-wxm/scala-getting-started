package com.wximperio.scala

object ScalaGeneric {
    def main(args: Array[String]): Unit = {
        val intMsg = new IntMsg[Int](18)
        println(intMsg)

        val strMsg = new StringMsg[String]("string")
        println(strMsg)

        val muMsg = new MultipleMsg[Double, Boolean, Float](10.0D, true, 0.5F)
        println(muMsg.booleanMsg)
        println(muMsg.doubleMsg)
        println(muMsg.floatMsg)
    }
}

abstract class Message[T](str: T) {
    def msg: T = str
}

class IntMsg[Int](value: Int) extends Message(value)

class StringMsg[String](value: String) extends Message(value)

class MultipleMsg[A, B, C](val doubleMsg: A, val booleanMsg: B, val floatMsg: C)

