package com.example.applicationtestsuite

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private var lastSelectedCounty: String? = null
    private var lastSelectedTown: String? = null

    private var isWeatherAnalysisEnabled = false
    private var isEntertainmentModeEnabled = false

    private val client = OkHttpClient()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        textView = findViewById(R.id.textView)

        val viewPager: ViewPager = findViewById(R.id.viewPager1)
//        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val adapter = MainPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

//        tabLayout.setupWithViewPager(viewPager)

        // --------------------------------------------------------------------------------------------

        val showPopupButton: ImageButton = findViewById(R.id.imagebutton2)
        showPopupButton.setOnClickListener {
            showPopup()
        }

        // -------------------------------

//        val showPopupButton2: ImageButton = findViewById(R.id.imagebutton)
//        showPopupButton2.setOnClickListener {
//            showPopup2()
//        }

        // --------------------------------------------------------------------------------------------

        DataHolder._county.value = "澎湖縣"
        DataHolder._town.value = "馬公市"

        updateLiveData()
    }

    private fun updateLiveData() {
        // Update the LiveData values
        DataHolder._county.value = "澎湖縣"
        DataHolder._town.value = "馬公市"
    }

//    沒有紀錄上次選擇
//    private fun showPopup() {
//        // 创建弹窗视图
//        val dialogView = layoutInflater.inflate(R.layout.popup_layout, null)
//
//        // 获取下拉菜单
//        val countySpinner: Spinner = dialogView.findViewById(R.id.countySpinner)
//        val townSpinner: Spinner = dialogView.findViewById(R.id.townSpinner)
//
//        // 设置县市数据
//        val counties = listOf("A", "B", "C", "D", "E")
//        val countyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, counties)
//        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        countySpinner.adapter = countyAdapter
//
//        // 设置默认乡镇数据
//        val townsA = listOf("1", "3", "5", "7")
//        val townAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, townsA)
//        townAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        townSpinner.adapter = townAdapter
//
//        // 根据县市选择更新乡镇数据
//        countySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedCounty = counties[position]
//                val towns = when (selectedCounty) {
//                    "A" -> listOf("1", "3", "5", "7")
//                    "B" -> listOf("25", "48", "19", "72")
//                    "C" -> listOf("4444", "2", "55", "7777", "0")
//                    "D" -> listOf("D1", "D2", "D3")  // Example data for D
//                    "E" -> listOf("E1", "E2", "E3")  // Example data for E
//                    else -> emptyList()
//                }
//                val newTownAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, towns)
//                newTownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                townSpinner.adapter = newTownAdapter
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing
//            }
//        }
//
//
//        // 创建并显示对话框
//        val dialog = AlertDialog.Builder(this)
//            .setTitle("Select Location")
//            .setView(dialogView)
//            .setPositiveButton("OK") { dialog, _ ->
//                // 获取选中的县市和乡镇
//                val selectedCounty = countySpinner.selectedItem.toString()
//                val selectedTown = townSpinner.selectedItem.toString()
//
//                // 将选中的值显示在TextView中
//                textView.text = "$selectedCounty $selectedTown"
//                dialog.dismiss()
//            }
//            .create()
//        dialog.show()
//    }

//    需要點擊 "OK" 才動作
//    private fun showPopup2() {
//        // 创建弹窗视图
//        val dialogView = layoutInflater.inflate(R.layout.popup_layout2, null)
//
//        // 获取ToggleButton
//        val weatherToggleButton: ToggleButton = dialogView.findViewById(R.id.weatherToggleButton)
//        val entertainmentToggleButton: ToggleButton = dialogView.findViewById(R.id.entertainmentToggleButton)
//
//        // 创建并显示对话框
//        val dialog = AlertDialog.Builder(this)
//            .setTitle("")
//            .setView(dialogView)
//            .setPositiveButton("OK") { dialog, _ ->
//                if (weatherToggleButton.isChecked) {
//                    // 气象分析模式被启用
//                    textView.text = "气象比较"
//                }
//                if (entertainmentToggleButton.isChecked) {
//                    // 娱乐模式被启用
//                    playVideo()
//                }
//                dialog.dismiss()
//            }
//            .create()
//        dialog.show()
//    }

//    點擊空白的部分就執行
//    private fun showPopup2() {
//        val dialogView = layoutInflater.inflate(R.layout.popup_layout2, null)
//
//        val weatherToggleButton: ToggleButton = dialogView.findViewById(R.id.weatherToggleButton)
//        val entertainmentToggleButton: ToggleButton = dialogView.findViewById(R.id.entertainmentToggleButton)
//
//        val dialog = AlertDialog.Builder(this)
//            .setTitle("")
//            .setView(dialogView)
//            .setOnDismissListener {
//                if (weatherToggleButton.isChecked) {
//                    textView.text = "气象比较"
//                }
//                if (entertainmentToggleButton.isChecked) {
//                    playVideo()
//                }
//            }
//            .create()
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.show()
//    }

    private fun showPopup() {
        val dialogView = layoutInflater.inflate(R.layout.popup_layout, null)

        val countySpinner: Spinner = dialogView.findViewById(R.id.countySpinner)
        val townSpinner: Spinner = dialogView.findViewById(R.id.townSpinner)
        fetchDataForSpinner1(countySpinner, townSpinner)


//
//        val counties = listOf("A", "B", "C", "D", "E")
//        val countyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, counties)
//        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        countySpinner.adapter = countyAdapter
//
//        val townsA = listOf("1", "3", "5", "7")
//        val townAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, townsA)
//        townAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        townSpinner.adapter = townAdapter
//
//        // 恢复上一次选择的县市和乡镇
//        lastSelectedCounty?.let { county ->
//            val position = counties.indexOf(county)
//            if (position >= 0) {
//                countySpinner.setSelection(position)
//            }
//        }
//
//        countySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedCounty = counties[position]
//                lastSelectedCounty = selectedCounty
//                val towns = when (selectedCounty) {
//                    "A" -> listOf("1", "3", "5", "7")
//                    "B" -> listOf("25", "48", "19", "72")
//                    "C" -> listOf("4444", "2", "55", "7777", "0")
//                    "D" -> listOf("D1", "D2", "D3")
//                    "E" -> listOf("E1", "E2", "E3")
//                    else -> emptyList()
//                }
//                val newTownAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, towns)
//                newTownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                townSpinner.adapter = newTownAdapter
//
//                // 恢复上一次选择的乡镇
//                lastSelectedTown?.let { town ->
//                    val townPosition = towns.indexOf(town)
//                    if (townPosition >= 0) {
//                        townSpinner.setSelection(townPosition)
//                    }
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing
//            }
//        }
//
//        townSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                lastSelectedTown = parent?.getItemAtPosition(position).toString()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing
//            }
//        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(" 區域選擇")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                // 获取选中的县市和乡镇
                val selectedCounty = countySpinner.selectedItem.toString()
                val selectedTown = townSpinner.selectedItem.toString()

                // 将选中的值显示在TextView中
                textView.text = "$selectedCounty $selectedTown"

//                DataHolder.setCounty("$selectedCounty")
//                DataHolder.setTown("$selectedTown")

                DataHolder._county.value = selectedCounty
                DataHolder._town.value = selectedTown

                dialog.dismiss()
            }
            .create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }

//    private fun showPopup2() {
//        val dialogView = layoutInflater.inflate(R.layout.popup_layout2, null)
//
//        val weatherToggleButton: ToggleButton = dialogView.findViewById(R.id.weatherToggleButton)
//        val entertainmentToggleButton: ToggleButton = dialogView.findViewById(R.id.entertainmentToggleButton)
//
//        // 恢复上次的选择状态
//        weatherToggleButton.isChecked = isWeatherAnalysisEnabled
//        entertainmentToggleButton.isChecked = isEntertainmentModeEnabled
//
//        val dialog = AlertDialog.Builder(this)
//            .setTitle("")
//            .setView(dialogView)
//            .setOnDismissListener {
//                // 保存当前的选择状态
//                isWeatherAnalysisEnabled = weatherToggleButton.isChecked
//                isEntertainmentModeEnabled = entertainmentToggleButton.isChecked
//
//                if (isWeatherAnalysisEnabled) {
//                    textView.text = "气象比较"
//                }
//                if (isEntertainmentModeEnabled) {
//                    playVideo()
//                }
//            }
//            .create()
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.show()
//    }


    private fun playVideo() {
        val videoUrl = "https://www.youtube.com/watch?v=8t8jLB_kKoA"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(intent)
    }

    private fun fetchDataForSpinner1(spinner1: Spinner, spinner2: Spinner) {
        val request = Request.Builder()
            .url("http://192.168.0.179/test/get_counties.php")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity", "Network request failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("MainActivity", "Unexpected response code: ${response.code}")
                    return
                }

                response.body?.let { responseBody ->
                    val jsonData = responseBody.string()
                    Log.d("MainActivity", "Received JSON: $jsonData")

                    val jsonArray = JSONArray(jsonData)
                    val dataList = mutableListOf<String>()
                    for (i in 0 until jsonArray.length()) {
                        dataList.add(jsonArray.getString(i))
                    }

                    runOnUiThread {
                        val adapter = ArrayAdapter(
                            this@MainActivity,
                            android.R.layout.simple_spinner_item,
                            dataList
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner1.adapter = adapter

                        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                                val selectedLocation = dataList[position]
                                fetchDataForSpinner2(spinner2, selectedLocation)
                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {
                                // Do nothing
                            }
                        }
                    }
                } ?: Log.e("MainActivity", "Response body is null")
            }
        })
    }

    private fun fetchDataForSpinner2(spinner2: Spinner, locationName: String) {
        val request = Request.Builder()
            .url("http://192.168.0.179/test/get_towns.php?locationsName=${locationName}")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity", "Network request failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("MainActivity", "Unexpected response code: ${response.code}")
                    return
                }

                response.body?.let { responseBody ->
                    val jsonData = responseBody.string()
                    Log.d("MainActivity", "Received JSON: $jsonData")

                    val jsonArray = JSONArray(jsonData)
                    val dataList = mutableListOf<String>()
                    for (i in 0 until jsonArray.length()) {
                        dataList.add(jsonArray.getString(i))
                    }

                    runOnUiThread {
                        val adapter = ArrayAdapter(
                            this@MainActivity,
                            android.R.layout.simple_spinner_item,
                            dataList
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner2.adapter = adapter
                    }
                } ?: Log.e("MainActivity", "Response body is null")
            }
        })
    }
}

// --------------------------------------------------------------------------------------------------------

// 循環分頁

//package com.example.applicationtestsuite
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.google.android.material.tabs.TabLayout
//import androidx.viewpager.widget.ViewPager
//import androidx.viewpager2.widget.ViewPager2
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
//        val adapter = CustomPagerAdapter(supportFragmentManager, lifecycle)
//        viewPager.adapter = adapter
//
//    }
//}
