package com.wxmimperio.scala.matching

object ScalaMatch {

    def matchDemo01: Unit = {
        val op = "10"
        val a = 5
        val b = 4
        val matchRetur = op match {
            case "+" => println(a + b)
            case "-" => println(a - b)
            case "*" => println(a * b)
            case "/" => println(a / b)
            case _ if op.toInt < 10 && op.toInt > 5 => println("3 < x < 10")
            case param => println("this is " + param)
            case _ => println("miss match")
        }
    }

    def matchDemo02: Unit = {
        // 根据param的类型去匹配
        val index = 3
        val param = if (index == 0) List(1, 2, 3)
        else if (index == 1) Map("a" -> 1, "b" -> 2)
        else if (index == 2) 123
        else if (index == 3) Array("dfas")
        else "aaa"

        param match {
            case a: List[Int] => println("List[Int]=" + a)
            case b: Map[String, Int] => println("Map[String, Int]=" + b)
            case c: BigInt => println("BigInt=" + c)
            case d: Array[String] => println("Array[String]=" + d)
            case _ => println("miss match")
        }
    }

    def matchDemo03: Unit = {
        val arrs = Array(Array(0), Array(5, 6), Array(0, "a", 1.1))
        for (arr <- arrs) {
            arr match {
                case Array(0) => println("Array(0)")
                case Array(x, y) => println("Array(x,y), x=" + x + ", y=" + y)
                case Array(0, _*) => println("Array(0,_*)")
                case _ => println("miss match")
            }
        }
    }

    def matchDemo04: Unit = {
        val arrs = List(List(0), List(5, 6), List(0, "a", 1.1))
        for (arr <- arrs) {
            arr match {
                case 0 :: Nil => println("0 :: Nil")
                case x :: y :: Nil => println("x :: y :: Nil" + x + ", y=" + y)
                case 0 :: tail => println("0 :: tail = " + tail)
                case _ => println("miss match")
            }
        }
    }

    def matchDemo05: Unit = {
        val arrs = List(List(0), List(5, 6), List(0, "a", 1.1))
        for (arr <- arrs) {
            arr match {
                case 0 :: Nil => println("0 :: Nil")
                case x :: y :: Nil => println("x :: y :: Nil" + x + ", y=" + y)
                case 0 :: tail => println("0 :: tail = " + tail)
                case _ => println("miss match")
            }
        }
    }

    def matchDemo06: Unit = {
        val arrs = List((0, 1), (10, 0), (1, 2, "wxm"))
        for (arr <- arrs) {
            arr match {
                case (0, _) => println("(0,_)")
                case (y, 0) => println("(y, 0), y = " + y)
                case (x, y, z) => println("(x, y, z), x = " + x + ", y = " + y + ", z = " + z)
                case _ => println("miss match")
            }
        }
    }

    def matchDemo07: Unit = {
        object Person {
            // 1. 对象提取器
            // 2. 返回一个Option的集合
            def unapply(arg: Double): Option[Double] = Some(Math.sqrt(arg)) // None则无法被匹配

            def apply(arg: Double): Double = arg * arg
        }

        val person: Double = 36
        person match {
            // 对象匹配，匹配对象中unapply方法返回Some的值
            // 会调用对象的unapply方法，返回Some中的值给传入的参数
            // 如果返回none，则表示没有匹配到
            case Person(n) => println(n)
            case _ => println("miss match")
        }
    }

    def matchDemo08: Unit = {
        object Person {
            // 1. 对象提取器
            // 2. 返回一个Option的Seq集合
            def unapplySeq(arg: String): Option[Seq[String]] = {
                println("in unapplySeq")
                if (arg.contains(",")) Some(arg.split(","))
                else None
            }
        }

        val person: String = "1,2,3"
        // 此时匹配的是多个参数，返回的是Some(1,2,3)
        person match {
            // 会去调用对象的unapplySeq方法，因为入参是多个
            // some返回值的个数，要和构造器入参的个数一致，否则无法匹配
            case Person(a, b, c) => println("a = " + a + ", b = " + b + ", c = " + c)
            case _ => println("miss match")
        }
    }

    def main(args: Array[String]): Unit = {
        //matchDemo01
        //matchDemo02
        //matchDemo03
        //matchDemo04
        //matchDemo05
        //matchDemo06
        //matchDemo07
        matchDemo08
    }
}
