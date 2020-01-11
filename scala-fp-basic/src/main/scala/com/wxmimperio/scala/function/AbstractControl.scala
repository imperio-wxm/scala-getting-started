package com.wxmimperio.scala.function

object AbstractControl {
    def main(args: Array[String]): Unit = {
        demo01
        demo02
    }

    def demo01: Unit = {
        def myThread(f1: () => Unit): Unit = {
            new Thread() {
                override def run(): Unit = {
                    f1()
                }
            }.start()
        }

        myThread {
            () => {
                println(Thread.currentThread().getName)
            }
        }
    }

    // 简写
    def demo02: Unit = {
        def myThread(f1: => Unit): Unit = {
            new Thread() {
                override def run(): Unit = {
                    f1
                }
            }.start()
        }

        myThread {
            println(Thread.currentThread().getName)
        }
    }
}
