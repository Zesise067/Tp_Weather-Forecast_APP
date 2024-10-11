package com.example.applicationtestsuite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class _NestedSecondFragment : Fragment() {

    private val client = OkHttpClient()

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView

    private lateinit var textView7: TextView

    private lateinit var textView11: TextView
    private lateinit var textView22: TextView
    private lateinit var textView33: TextView
    private lateinit var textView44: TextView
    private lateinit var textView55: TextView
    private lateinit var textView66: TextView

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var imageView4: ImageView
    private lateinit var imageView5: ImageView
    private lateinit var imageView6: ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout._fragment_nested_second, container, false)

        textView1 = view.findViewById(R.id.textView1)
        textView2 = view.findViewById(R.id.textView2)

        textView7 = view.findViewById(R.id.textView7)

        textView11 = view.findViewById(R.id.textView11)
        textView22 = view.findViewById(R.id.textView22)
        textView33 = view.findViewById(R.id.textView33)
        textView44 = view.findViewById(R.id.textView44)
        textView55 = view.findViewById(R.id.textView55)
        textView66 = view.findViewById(R.id.textView66)

        imageView1 = view.findViewById(R.id.imageView1)
        imageView2 = view.findViewById(R.id.imageView2)
        imageView3 = view.findViewById(R.id.imageView3)
        imageView4 = view.findViewById(R.id.imageView4)
        imageView5 = view.findViewById(R.id.imageView5)
        imageView6 = view.findViewById(R.id.imageView6)


//         Observe the LiveData from DataHolder
//        DataHolder._county.observe(viewLifecycleOwner, Observer { county ->
//            textView1.text = county.toString()
//        })
//
//        DataHolder._town.observe(viewLifecycleOwner, Observer { town ->
//            textView2.text = town.toString()
//        })

        // -------------------------------------------------------------------------------------------

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
                fetchAndDisplayData(value1, value2, value3)
            }
        })
        return view
    }

//    private fun fetchAndDisplayData(_county:String, _town:String, datetime:String) {
//
////        val url = "http://192.168.0.179/test/test__Respond_to_requests2.php?locationsName=宜蘭縣&locationName=頭城鎮&dataTime=$datetime"
//        println("$_county $_town")
////        val url = "http://192.168.0.179/test/test__Respond_to_requests2.php?locationsName=" + _county + "&locationName=" + _town + "&dataTime=" + datetime
//        val url = String.format("http://192.168.0.179/test/test__Respond_to_requests3.php?locationsName=%s&locationName=%s&dataTime=%s", _county, _town, datetime)
//
//        val request = Request.Builder()
//            .url(url)
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val responseData = response.body?.string()
//                if (responseData != null) {
//                    try {
//                        println("Response Data: $responseData") // Debug: Print response data
//
//                        val jsonArray = JSONArray(responseData)
//
//                        // Initialize variables to store rh values
//                        var rhValue1: String? = null
//                        var rhValue2: String? = null
//
//                        for (i in 0 until jsonArray.length()) {
//                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
//                            val dataTime = jsonObject.getString("dataTime")
//
//                            // 定義日期時間格式
//                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//
//                            // 解析日期時間字符串
//                            val dateTime = LocalDateTime.parse(dataTime, formatter)
//
//                            // 提取日期部分
//                            val datePart = dateTime.toLocalDate()
//
//                            // 格式化日期部分為字串
//                            val formattedDate = datePart.format(DateTimeFormatter.ofPattern("HH:mm"))
//
//
//
//
//                            if (formattedDate == "00:00") {
//                                // Found the first target datetime, get rh value
////                                rhValue1 = jsonObject.getString("wx")
//
//                                textView11.text = jsonObject.getString("t")
//
//                            } else if (formattedDate == "06:00") {
//                                // Found the second target datetime, get rh value
////                                rhValue2 = jsonObject.getString("t")
//
//                                textView22.text = jsonObject.getString("t")
//
//                            }
//
//                            // Exit loop once both values are found
//                            if (rhValue1 != null && rhValue2 != null) {
//                                break
//                            }
//                        }
//
////                        GlobalScope.launch(Dispatchers.Main) {
////                            // Update UI with rh values
////                            if (rhValue1 != null && rhValue2 != null) {
////                                textView.text = "Relative Humidity (RH) at $datetime1: $rhValue1%\n" +
////                                        "Relative Humidity (RH) at $datetime2: $rhValue2%"
////                            } else {
////                                textView.text = "Data for times $datetime1 and $datetime2 not found"
////                            }
////                        }
//
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                        GlobalScope.launch(Dispatchers.Main) {
//                            textView1.text = "Failed to parse JSON: ${e.message}" // Output exception message
//                        }
//                    }
//                } else {
//                    GlobalScope.launch(Dispatchers.Main) {
//                        textView1.text = "No response data"
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                GlobalScope.launch(Dispatchers.Main) {
//                    textView1.text = "Request failed: ${e.message}"
//                }
//            }
//        })
//    }

    private fun fetchAndDisplayData(_county: String, _town: String, datetime: String) {
        // Define the date time formatter
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        // Parse the datetime string into a LocalDateTime object
        val originalDateTime = LocalDateTime.parse(datetime, formatter)

        // Add one day to the LocalDateTime object
        val updatedDateTime = originalDateTime.plusDays(1)

        // Convert the updated LocalDateTime object back to a string
        val newDateTime = updatedDateTime.format(formatter)

        val url = String.format(
            "http://192.168.0.179/test/test__Respond_to_requests3.php?locationsName=%s&locationName=%s&dataTime=%s",
            _county,
            _town,
            newDateTime
        )

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (responseData != null) {
                    try {
                        println("Response Data: $responseData") // Debug: Print response data

                        val jsonArray = JSONArray(responseData)

                        // Loop through JSON array
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                            val dataTime = jsonObject.getString("dataTime")
                            val detailsArray = jsonObject.getJSONArray("details")

                            // Loop through details array (should be only one object based on your structure)
                            for (j in 0 until detailsArray.length()) {
                                val detailObject = detailsArray.getJSONObject(j)
                                val tValue = detailObject.getString("t")
                                val wx = detailObject.getString("wx")

                                var text = ""

                                // Update UI with t and wx values based on conditions
                                GlobalScope.launch(Dispatchers.Main) {
                                    when {
                                        dataTime.endsWith(" 00:00:00") -> {
                                            text = tValue + "°"
                                            textView11.text = text

                                            if (wx == "陰") {
                                                imageView2.setImageResource(R.drawable.cloudyday)
                                            } else if (wx == "晴") {
                                                imageView2.setImageResource(R.drawable.clear)
                                            } else if (wx == "短暫雨" || wx == "短暫陣雨") {
                                                imageView2.setImageResource(R.drawable.rain)
                                            } else if (wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨") {
                                                imageView2.setImageResource(R.drawable.thunderstorm)
                                            } else if (wx == "短暫陣雨有霧") {
                                                imageView2.setImageResource(R.drawable.fog)
                                            } else if (wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨") {
                                                imageView2.setImageResource(R.drawable.briefshowers)
                                            } else {
                                                imageView2.setImageResource(R.drawable.partlycloudy)
                                            }
                                        }

                                        dataTime.endsWith(" 03:00:00") -> {
                                            text = tValue + "°"
                                            textView22.text = text

                                            if (wx == "陰") {
                                                imageView3.setImageResource(R.drawable.cloudyday)
                                            } else if (wx == "晴") {
                                                imageView3.setImageResource(R.drawable.clear)
                                            } else if (wx == "短暫雨" || wx == "短暫陣雨") {
                                                imageView3.setImageResource(R.drawable.rain)
                                            } else if (wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨") {
                                                imageView3.setImageResource(R.drawable.thunderstorm)
                                            } else if (wx == "短暫陣雨有霧") {
                                                imageView3.setImageResource(R.drawable.fog)
                                            } else if (wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨") {
                                                imageView3.setImageResource(R.drawable.briefshowers)
                                            } else {
                                                imageView3.setImageResource(R.drawable.partlycloudy)
                                            }
                                        }

                                        dataTime.endsWith(" 09:00:00") -> {
                                            text = tValue + "°"
                                            textView33.text = text

                                            if (wx == "陰") {
                                                imageView1.setImageResource(R.drawable.cloudyday)
                                            } else if (wx == "晴") {
                                                imageView1.setImageResource(R.drawable.clear)
                                            } else if (wx == "短暫雨" || wx == "短暫陣雨") {
                                                imageView1.setImageResource(R.drawable.rain)
                                            } else if (wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨") {
                                                imageView1.setImageResource(R.drawable.thunderstorm)
                                            } else if (wx == "短暫陣雨有霧") {
                                                imageView1.setImageResource(R.drawable.fog)
                                            } else if (wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨") {
                                                imageView1.setImageResource(R.drawable.briefshowers)
                                            } else {
                                                imageView1.setImageResource(R.drawable.partlycloudy)
                                            }
                                        }

                                        dataTime.endsWith(" 12:00:00") -> {
                                            text = tValue + "°"
                                            textView44.text = text

                                            if (wx == "陰") {
                                                imageView4.setImageResource(R.drawable.cloudyday)
                                            } else if (wx == "晴") {
                                                imageView4.setImageResource(R.drawable.clear)
                                            } else if (wx == "短暫雨" || wx == "短暫陣雨") {
                                                imageView4.setImageResource(R.drawable.rain)
                                            } else if (wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨") {
                                                imageView4.setImageResource(R.drawable.thunderstorm)
                                            } else if (wx == "短暫陣雨有霧") {
                                                imageView4.setImageResource(R.drawable.fog)
                                            } else if (wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨") {
                                                imageView4.setImageResource(R.drawable.briefshowers)
                                            } else {
                                                imageView4.setImageResource(R.drawable.partlycloudy)
                                            }
                                        }

                                        dataTime.endsWith(" 15:00:00") -> {
                                            text = tValue + "°"
                                            textView55.text = text

                                            if (wx == "陰") {
                                                imageView5.setImageResource(R.drawable.cloudyday)
                                            } else if (wx == "晴") {
                                                imageView5.setImageResource(R.drawable.clear)
                                            } else if (wx == "短暫雨" || wx == "短暫陣雨") {
                                                imageView5.setImageResource(R.drawable.rain)
                                            } else if (wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨") {
                                                imageView5.setImageResource(R.drawable.thunderstorm)
                                            } else if (wx == "短暫陣雨有霧") {
                                                imageView5.setImageResource(R.drawable.fog)
                                            } else if (wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨") {
                                                imageView5.setImageResource(R.drawable.briefshowers)
                                            } else {
                                                imageView5.setImageResource(R.drawable.partlycloudy)
                                            }
                                        }

                                        dataTime.endsWith(" 21:00:00") -> {
                                            text = tValue + "°"
                                            textView66.text = text

                                            if (wx == "陰") {
                                                imageView6.setImageResource(R.drawable.cloudyday)
                                            } else if (wx == "晴") {
                                                imageView6.setImageResource(R.drawable.clear)
                                            } else if (wx == "短暫雨" || wx == "短暫陣雨") {
                                                imageView6.setImageResource(R.drawable.rain)
                                            } else if (wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨") {
                                                imageView6.setImageResource(R.drawable.thunderstorm)
                                            } else if (wx == "短暫陣雨有霧") {
                                                imageView6.setImageResource(R.drawable.fog)
                                            } else if (wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨") {
                                                imageView6.setImageResource(R.drawable.briefshowers)
                                            } else {
                                                imageView6.setImageResource(R.drawable.partlycloudy)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                        GlobalScope.launch(Dispatchers.Main) {
                            textView7.text =
                                "Failed to parse JSON: ${e.message}" // Output exception message
                        }
                    }
                } else {
                    GlobalScope.launch(Dispatchers.Main) {
                        textView7.text = "No response data"
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                GlobalScope.launch(Dispatchers.Main) {
                    textView7.text = "Request failed: ${e.message}"
                }
            }
        })
    }
}