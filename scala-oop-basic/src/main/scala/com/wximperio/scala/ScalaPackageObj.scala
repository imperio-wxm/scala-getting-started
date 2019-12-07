package com.wximperio {

    // 1. 声明一个包对象
    // 2. 它是com.wximperio.scala的包对象
    // 3. 每一个包只能有一个包对象，且只能在父包中定义，包对象和包是平级关系
    // 4. 包对象的名称必须与对应的包一直
    // 5. 在包对象中可以定义变量和方法
    // 6. 在此包中可以直接使用包对象中的变量和方法
    // 7. 包对象会生成两个class，package.class 和 package$.class
    // 8. 包对象的变量、函数都会生成在package.class 和 package$.class文件中，调用时是去找这两个class
    package object scala {
        var a: String = _

        def test(): Unit = {
            a = "wxm"
            println(a)
        }
    }

    package scala {

        object ScalaPackageObj {
            def main(args: Array[String]): Unit = {
                // 使用包对象的方法，变量
                scala.a
                test()
            }
        }
    }

}
