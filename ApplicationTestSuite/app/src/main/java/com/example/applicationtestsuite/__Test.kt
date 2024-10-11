package com.example.applicationtestsuite

class __Test {
    // 宣告一個私有變數
    private var myVar: Int = 0

    // 取得變數的函式
    fun getMyVar(): Int {
        return myVar
    }

    // 設定變數的函式
    fun setMyVar(value: Int) {
        myVar = value
    }
}

fun main() {
    val test = __Test()

    // 設定變數
    test.setMyVar(10)

    // 取得變數
    println(test.getMyVar()) // 這會輸出 10
}
