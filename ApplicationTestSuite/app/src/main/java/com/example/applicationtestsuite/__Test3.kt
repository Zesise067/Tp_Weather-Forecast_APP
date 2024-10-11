package com.example.applicationtestsuite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class __Test3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.__test2) // 確保這是你的佈局檔案

        // 建立 __Test 物件並設定 myVar 的值
        val test = __Test()
        test.setMyVar(10)

        // 取得 myVar 的值
//        val myVarValue = test.getMyVar()

        // 取得 TextView 並設置它的文本內容
//        val textView = findViewById<TextView>(R.id.TextView5j)
//        textView.text = myVarValue.toString()
    }
}
