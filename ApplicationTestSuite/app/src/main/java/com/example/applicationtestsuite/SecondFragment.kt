package com.example.applicationtestsuite

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SecondFragment : Fragment() {

    private val client = OkHttpClient()

    private lateinit var textView: TextView
    private lateinit var buttonSelectDateTime: Button

//    private lateinit var _county: String
//    private lateinit var _town: String
    private lateinit var _county: String
    private lateinit var _town: String

//    private lateinit var dataHolderViewModel: DataHolderViewModel

//    private lateinit var currentDateTime: String // 或者使用可为空的 String? 类型： private var currentDateTime: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        // -----------------------------------------------------------------------------------------------

        // 標籤
        var textView1: TextView = view.findViewById(R.id.textView01)
        var textView2: TextView = view.findViewById(R.id.textView02)
        var textView3: TextView = view.findViewById(R.id.textView03)
        var textView4: TextView = view.findViewById(R.id.textView04)
        var textView5: TextView = view.findViewById(R.id.textView05)
        var textView6: TextView = view.findViewById(R.id.textView06)
        var textView7: TextView = view.findViewById(R.id.textView07)

        DataHolder._t.observe(viewLifecycleOwner, Observer { t ->
            textView1.text = t.toString() + " \u00B0C"
        })

        DataHolder._at.observe(viewLifecycleOwner, Observer { at ->
            textView2.text = at.toString() + " \u00B0C"
        })

        DataHolder._td.observe(viewLifecycleOwner, Observer { td ->
            textView3.text = td.toString() + " \u00B0C"
        })

        // ------------

        DataHolder._rh.observe(viewLifecycleOwner, Observer { rh ->
            textView4.text = rh.toString() + " %"
        })

        DataHolder._pop6h.observe(viewLifecycleOwner, Observer { pop6h ->
            textView5.text = pop6h.toString() + " %"
        })

        // ------------

        DataHolder._wd.observe(viewLifecycleOwner, Observer { wd ->
            textView6.text = wd.toString()
        })

        DataHolder._ws.observe(viewLifecycleOwner, Observer { ws ->
            textView7.text = ws.toString() + " m/s"
        })

        // -----------------------------------------------------------------------------------------------

        // 按鈕
        val updateButton: Button = view.findViewById(R.id.updateButton)
        val updateButton2: Button = view.findViewById(R.id.updateButton2)
        val updateButton3: Button = view.findViewById(R.id.updateButton3)
        val updateButton4: Button = view.findViewById(R.id.updateButton4)
        val updateButton5: Button = view.findViewById(R.id.updateButton5)
        val updateButton6: Button = view.findViewById(R.id.updateButton6)
        val updateButton7: Button = view.findViewById(R.id.updateButton7)

        updateButton.setOnClickListener {DataHolder._displayGraphics.value = "t"}
        updateButton2.setOnClickListener {DataHolder._displayGraphics.value = "at"}
        updateButton3.setOnClickListener {DataHolder._displayGraphics.value = "td"}

        updateButton4.setOnClickListener {DataHolder._displayGraphics.value = "rh"}
        updateButton5.setOnClickListener {DataHolder._displayGraphics.value = "pop6h"}
        updateButton6.setOnClickListener {DataHolder._displayGraphics.value = "ws"}
        updateButton7.setOnClickListener {DataHolder._displayGraphics.value = "ws"}

        // -----------------------------------------------------------------------------------------------

        _county = ""
        _town = ""

        textView = view.findViewById(R.id.textView)
        buttonSelectDateTime = view.findViewById(R.id.textViewSelectDateTime)

        // Initialize ViewModel
//        dataHolderViewModel = ViewModelProvider(this).get(DataHolderViewModel::class.java)

        // Observe the LiveData from DataHolder
        DataHolder._county.observe(viewLifecycleOwner, Observer { county ->
            textView.text = county.toString()
            _county = county.toString()
            println("Response Data: $_county") // Debug: Print response data
        })

        // Observe the LiveData from DataHolder
        DataHolder._town.observe(viewLifecycleOwner, Observer { town ->
            _town = town.toString()
            println("Response Data: $_town") // Debug: Print response data
        })
        println("Response Data: $_town") // Debug: Print response data
        DataHolder._currentTime.observe(viewLifecycleOwner, Observer { currentTime ->
            buttonSelectDateTime.text = currentTime.toString()
//            currentDateTime = currentTime.toString() // Store currentTime in currentDateTime variable
        })


        val lineChart: LineChart = view.findViewById(R.id.lineChart)

//        時間日期選擇 - 暫時關必
        val buttonSelectDateTime: Button = view.findViewById(R.id.textViewSelectDateTime)

//        fun selectDateTime(onDateTimeSelected: (String) -> Unit) {
//            // This is a placeholder for the actual date-time picker logic
//            // Let's assume it returns a LocalDateTime for demonstration purposes
//            val selectedDateTime = LocalDateTime.now() // Replace with actual selected date-time
//            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//            val formattedDateTime = selectedDateTime.format(formatter)
//            onDateTimeSelected(formattedDateTime)
//        }

        buttonSelectDateTime.setOnClickListener {
            selectDateTime { selectedDateTime ->
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val dateTime = LocalDateTime.parse(selectedDateTime, formatter)

                val formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val formattedDateTime = dateTime.format(formatter2)

                buttonSelectDateTime.text = formattedDateTime
                DataHolder._currentTime.value = formattedDateTime
                DataHolder._customTime.value = false
            }
        }

        val buttonReturn: ImageButton = view.findViewById(R.id.imageView10)

//        fun selectDateTime(onDateTimeSelected: (String) -> Unit) {
//            // This is a placeholder for the actual date-time picker logic
//            // Let's assume it returns a LocalDateTime for demonstration purposes
//            val selectedDateTime = LocalDateTime.now() // Replace with actual selected date-time
//            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//            val formattedDateTime = selectedDateTime.format(formatter)
//            onDateTimeSelected(formattedDateTime)
//        }

        buttonReturn.setOnClickListener {
            DataHolder._customTime.value = true
        }

        // --------------------------------------------------------------------------------------------

//        三個參數
//         Create a MediatorLiveData to observe both town and other LiveData sources
//        val combinedData = MediatorLiveData<Triple<String?, String?, String?>>().apply {
//            var latestCounty: String? = null
//            var latestTown: String? = null
//            var latestDateTime: String? = null
//
//            addSource(DataHolder._county) { county ->
//                latestCounty = county
//                value = Triple(latestCounty, latestTown, latestDateTime)
//            }
//
//            addSource(DataHolder._town) { town ->
//                latestTown = town
//                value = Triple(latestCounty, latestTown, latestDateTime)
//            }
//
//            addSource(DataHolder._currentTime) { currentTime ->
//                latestDateTime = currentTime
//                value = Triple(latestCounty, latestTown, latestDateTime)
//            }
//        }
//
//        // Observe the combinedData and call fetchAndDisplayData when both values are not null
//        combinedData.observe(viewLifecycleOwner, Observer { triple ->
//            val (value, value2, value3) = triple
//            if (value != null && value2 != null && value3 != null) {
////                fetchAndDisplayData(value, value2, value3)
//                fetchAndDisplayData(lineChart, textView, value, value2, value3)
//            }
//        })

//        四個參數
        val combinedData = MediatorLiveData<List<String?>>().apply {
            var latestCounty: String? = null
            var latestTown: String? = null
            var latestDateTime: String? = null
            var latestDisplayGraphics: String? = null

            addSource(DataHolder._county) { county ->
                latestCounty = county
                value = listOf(latestCounty, latestTown, latestDateTime, latestDisplayGraphics)
            }

            addSource(DataHolder._town) { town ->
                latestTown = town
                value = listOf(latestCounty, latestTown, latestDateTime, latestDisplayGraphics)
            }

            addSource(DataHolder._currentTime) { currentTime ->
                latestDateTime = currentTime
                value = listOf(latestCounty, latestTown, latestDateTime, latestDisplayGraphics)
            }

            addSource(DataHolder._displayGraphics) { displayGraphics ->
                latestDisplayGraphics = displayGraphics
                value = listOf(latestCounty, latestTown, latestDateTime, latestDisplayGraphics)
            }
        }

        // Observe the combinedData and call fetchAndDisplayData when both values are not null
        combinedData.observe(viewLifecycleOwner, Observer { list ->
            val (value1, value2, value3, value4) = list
            if (value1 != null && value2 != null && value3 != null && value4 != null) {
                fetchAndDisplayData(lineChart, textView, value1, value2, value3, value4)
            }
        })

        DataHolder._town.value = "七美鄉"

        return view
    }

    private fun selectDateTime(callback: (String) -> Unit) {
        val calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                val selectedDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(calendar.time)
                callback(selectedDateTime)
            }
            TimePickerDialog(requireContext(), timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true).show()
        }

        DatePickerDialog(requireContext(), dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun fetchAndDisplayData(lineChart: LineChart, textView: TextView, _county:String, _town:String, datetime:String, displayGraphics:String) {

//        val url = "http://192.168.0.179/test/test__Respond_to_requests2.php?locationsName=宜蘭縣&locationName=頭城鎮&dataTime=$datetime"
        println("$_county $_town")
//        val url = "http://192.168.0.179/test/test__Respond_to_requests2.php?locationsName=" + _county + "&locationName=" + _town + "&dataTime=" + datetime
        val url = String.format("http://192.168.0.179/test/test__Respond_to_requests2.php?locationsName=%s&locationName=%s&dataTime=%s", _county, _town, datetime)

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (responseData != null) {
                    try {
//                        println("Response Data: $responseData") // Debug: Print response data

                        val jsonArray = JSONArray(responseData)
                        val entriesT = ArrayList<Entry>()
                        val entriesAT = ArrayList<Entry>()
                        val entriesTD = ArrayList<Entry>()

                        val entriesRH = ArrayList<Entry>()
                        val entriesPoP6h = ArrayList<Entry>()

                        val entriesWS = ArrayList<Entry>()

                        val dateLabels = ArrayList<String>()

                        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        val displayFormat = SimpleDateFormat("MMdd-HH", Locale.getDefault())

                        // 用于存储第一条数据
                        var firstData: JSONObject? = null

                        var dataLost: Boolean = false

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                            if (i == 0) {
//                                // 存储第一条数据
//                                firstData = jsonObject
//                                val valueT1 = jsonObject.getInt("at").toFloat()
//                                DataHolder.setAT(valueT1)

                                val handler = Handler(Looper.getMainLooper())
                                handler.post {
                                    // 在這裡設置 UI 元素的值
                                    DataHolder.setAT(jsonObject.getInt("at").toString())
                                    DataHolder.setCI(jsonObject.getString("ci").toString())
                                    DataHolder.setPoP6h(jsonObject.getInt("pop6h").toString())
                                    DataHolder.setRH(jsonObject.getInt("rh").toString())

                                    DataHolder._t.value = jsonObject.getInt("t").toString()
//                                    DataHolder.setT(jsonObject.getInt("t").toString())

                                    DataHolder.setT(jsonObject.getInt("t").toString())
                                    DataHolder.setTD(jsonObject.getInt("td").toString())
                                    DataHolder.setWD(jsonObject.getString("wd").toString())
                                    DataHolder.setWS(jsonObject.getString("ws").toString())
                                    DataHolder.setWX(jsonObject.getString("wx").toString())
                                    DataHolder.setMax_T(jsonObject.getInt("max_t").toString())
                                    DataHolder.setMin_T(jsonObject.getInt("min_t").toString())

//                                    val at = if (jsonObject.has("at")) jsonObject.getInt("at").toString() else "lost"
//                                    dataLost = at == null // 若 at 為 "lost"，則設置 dataLost 為 true

                                }
                            }
                            val dateString = jsonObject.getString("dataTime")
                            val date: Date = dateFormat.parse(dateString)!!
                            val timeInMillis = i.toFloat() // Use the index as X value

                            if (displayGraphics == "t" || displayGraphics == "at" || displayGraphics == "td") {
                                val valueT = jsonObject.getInt("t").toFloat() // Use the "t" field for data values
                                val valueAT = jsonObject.getInt("at").toFloat()
                                val valueTd = jsonObject.getInt("td").toFloat() // Use the "td" field for data values
                                entriesT.add(Entry(timeInMillis, valueT))
                                entriesAT.add(Entry(timeInMillis, valueAT))
                                entriesTD.add(Entry(timeInMillis, valueTd))
                            }else if (displayGraphics == "rh" || displayGraphics == "pop6h") {
                                val valueRH = jsonObject.getInt("rh").toFloat()
                                val valuePoP6h = jsonObject.getInt("pop6h").toFloat()
                                entriesRH.add(Entry(timeInMillis, valueRH))
                                entriesPoP6h.add(Entry(timeInMillis, valuePoP6h))
                            }else if (displayGraphics == "ws"|| displayGraphics == "wd") {
                                val valueWS = jsonObject.getInt("ws").toFloat()
                                entriesWS.add(Entry(timeInMillis, valueWS))
                            }

                            dateLabels.add(displayFormat.format(date))
                        }

//                         在这里可以处理或使用 firstData
//                        if (firstData != null) {
//                            // 例如，您可以打印 firstData
//                            println("First data: $firstData")
//                        }

                        GlobalScope.launch(Dispatchers.Main) {
                            var dataSet = LineDataSet(entriesT, "")
                            var dataSet2 = LineDataSet(entriesT, "")
                            var dataSet3 = LineDataSet(entriesT, "")

                            var lineData = LineData(dataSet)

                            if (displayGraphics == "t" || displayGraphics == "at" || displayGraphics == "td") {
                                dataSet = LineDataSet(entriesT, "溫度")
//                                dataSetT.color = Color.GREEN // 设置线条颜色为红色
                                dataSet.lineWidth = 2f // 设置线条宽度

                                dataSet2 = LineDataSet(entriesAT, "體感溫度")
                                dataSet2.color = Color.GREEN // 设置线条颜色为蓝色
                                dataSet2.lineWidth = 2f // 设置线条宽度

                                dataSet3 = LineDataSet(entriesTD, "露點溫度")
                                dataSet3.color = Color.YELLOW // 设置线条颜色为蓝色
                                dataSet3.lineWidth = 2f // 设置线条宽度

                                lineData = LineData(dataSet, dataSet2, dataSet3)

                            }else if (displayGraphics == "rh" || displayGraphics == "pop6h") {
                                dataSet = LineDataSet(entriesRH, "相對濕度")
//                                dataSetRH.color = Color.BLUE // 设置线条颜色为蓝色
                                dataSet.lineWidth = 2f // 设置线条宽度

                                dataSet2 = LineDataSet(entriesPoP6h, "降雨機率")
                                dataSet2.color = Color.GREEN // 设置线条颜色为蓝色
                                dataSet2.lineWidth = 2f // 设置线条宽度

                                lineData = LineData(dataSet, dataSet2)

                            }else if (displayGraphics == "ws"|| displayGraphics == "wd") {

                                dataSet = LineDataSet(entriesWS, "風速")
//                                dataSetWS.color = Color.BLUE // 设置线条颜色为蓝色
                                dataSet.lineWidth = 2f // 设置线条宽度

                                lineData = LineData(dataSet)
                            }

                            lineChart.data = lineData

                            // Customize X-axis labels
                            val xAxis: XAxis = lineChart.xAxis
                            xAxis.position = XAxis.XAxisPosition.BOTTOM
                            xAxis.valueFormatter = object : ValueFormatter() {
                                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                                    val index = value.toInt()
                                    return if (index >= 0 && index < dateLabels.size) {
                                        dateLabels[index]
                                    } else {
                                        ""
                                    }
                                }
                            }

                            xAxis.granularity = 1f // Ensure labels are evenly spaced
                            xAxis.isGranularityEnabled = true

                            lineChart.invalidate() // Refresh the chart


                            if (dataLost == true) {
                                textView.text = "\n    No Data"
                            }else{
                                textView.text = "\n    Data loaded"
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        GlobalScope.launch(Dispatchers.Main) {
                            textView.text = "Failed to parse data: ${e.message}" // Output the exception message
                        }
                    }
                } else {
                    GlobalScope.launch(Dispatchers.Main) {
                        textView.text = "No response data"
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                GlobalScope.launch(Dispatchers.Main) {
                    textView.text = "Request failed: ${e.message}"
                }
            }
        })
    }
}
