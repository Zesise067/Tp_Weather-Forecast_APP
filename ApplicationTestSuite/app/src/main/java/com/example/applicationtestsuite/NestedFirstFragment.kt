package com.example.applicationtestsuite

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

class NestedFirstFragment : Fragment() {

    private lateinit var textViewSelectDateTime: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_nested_first, container, false)

        textViewSelectDateTime = view.findViewById(R.id.textViewSelectDateTime)

        DataHolder._currentTime.observe(viewLifecycleOwner, Observer { currentTime ->
            textViewSelectDateTime.text = currentTime.toString()
        })

        val textView: TextView = view.findViewById(R.id.textView5)
        var text = ""

        val combinedData = MediatorLiveData<List<String?>>().apply {
            var latest_wx: String? = null
            var latest_pop6h: String? = null
            var latest_t: String? = null
            var latest_ci: String? = null
            var latest_rh: String? = null
            var latest_wd: String? = null
            var latest_ws: String? = null

            addSource(DataHolder._wx) { wx ->
                latest_wx = wx
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }

            addSource(DataHolder._pop6h) { town ->
                latest_pop6h = town
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }

            addSource(DataHolder._t) { currentTime ->
                latest_t = currentTime
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }

            addSource(DataHolder._ci) { displayGraphics ->
                latest_ci = displayGraphics
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }

            addSource(DataHolder._rh) { displayGraphics ->
                latest_rh = displayGraphics
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }

            addSource(DataHolder._wd) { wd ->
                latest_wd = wd
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }

            addSource(DataHolder._ws) { ws ->
                latest_ws = ws
                value = listOf(latest_wx, latest_pop6h, latest_t, latest_ci, latest_rh, latest_wd, latest_ws)
            }
        }

        // Observe the combinedData and call fetchAndDisplayData when both values are not null
        combinedData.observe(viewLifecycleOwner, Observer { list ->
//            val (value1, value2, value3, value4, value5, value6, value7) = list
//            if (value1 != null && value2 != null && value3 != null && value4 != null && value5 != null && value6 != null && value7 != null) {
            val (value1, value2, value3, value4, value5) = list
            if (value1 != null && value2 != null && value3 != null && value4 != null && value5 != null) {
                text = """
                    &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                    今日天氣預報顯示$value1，
                    降雨機率為 $value2%，
                    溫度約攝氏 $value3°，
                    感覺$value4，
                    相對濕度約 $value5%。
                """.trimIndent()
                textView.text = Html.fromHtml(text)
            }
        })

        // -------------------------------------------------------------------------------------------
//
//        // 假設有10個
//
//        import androidx.lifecycle.LiveData
//                import androidx.lifecycle.MediatorLiveData
//                import androidx.lifecycle.Observer
//
//                override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View? {
//            // Inflate the layout for this fragment
//            val view = inflater.inflate(R.layout.fragment_second, container, false)
//
//            val dataSources = listOf(
//                DataHolder.source1,
//                DataHolder.source2,
//                DataHolder.source3,
//                DataHolder.source4,
//                DataHolder.source5,
//                DataHolder.source6,
//                DataHolder.source7,
//                DataHolder.source8,
//                DataHolder.source9,
//                DataHolder.source10
//            )
//
//            val combinedData = MediatorLiveData<List<Any?>>().apply {
//                val latestValues = MutableList<Any?>(dataSources.size) { null }
//
//                dataSources.forEachIndexed { index, source ->
//                    addSource(source) { newValue ->
//                        latestValues[index] = newValue
//                        value = latestValues.toList()  // Update the MediatorLiveData value
//                    }
//                }
//            }
//
//            combinedData.observe(viewLifecycleOwner, Observer { values ->
//                if (values.all { it != null }) {
//                    fetchAndDisplayData(*values.toTypedArray())
//                }
//            })
//
//            return view
//        }
//
//        // Example DataHolder object with 10 LiveData sources
//        object DataHolder {
//            val source1 = MutableLiveData<String>()
//            val source2 = MutableLiveData<String>()
//            val source3 = MutableLiveData<String>()
//            val source4 = MutableLiveData<String>()
//            val source5 = MutableLiveData<String>()
//            val source6 = MutableLiveData<String>()
//            val source7 = MutableLiveData<String>()
//            val source8 = MutableLiveData<String>()
//            val source9 = MutableLiveData<String>()
//            val source10 = MutableLiveData<String>()
//        }
//
//        // Example fetchAndDisplayData function
//        fun fetchAndDisplayData(vararg values: Any?) {
//            // Implement your data fetching and display logic here
//        }
//
        // -------------------------------------------------------------------------------------------------

        return view
    }
}
