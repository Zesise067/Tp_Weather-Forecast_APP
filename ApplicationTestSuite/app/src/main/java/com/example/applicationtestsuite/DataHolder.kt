package com.example.applicationtestsuite

// 月份減一個月
import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

object DataHolder {
    val _county = MutableLiveData<String>().apply { value = "澎湖縣" }
    val _town = MutableLiveData<String>()
    val _displayGraphics = MutableLiveData<String>().apply { value = "t" }

//    private val _county = MutableLiveData<String>().apply { value = "澎湖縣" }
//    val county: LiveData<String> get() = _county
//
//    private val _town = MutableLiveData<String>().apply { value = "馬公市" }
//    val town: LiveData<String> get() = _town

    fun setCounty(value: String) {
        _county.value = value
    }

    fun setTown(value: String) {
        _town.value = value
    }

    val _currentTime = MutableLiveData<String>()

    val _customTime = MutableLiveData<Boolean>().apply { value = true }

//    private val _currentTime = MutableLiveData<String>()
//    val currentTime: LiveData<String> get() = _currentTime

    init {
        updateTime()
    }

//    private fun updateTime() {
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
//        val calendar = Calendar.getInstance()
//
//        // 将月份减去一个月
//        calendar.add(Calendar.MONTH, -1)
//
//        val currentTimeString = dateFormat.format(calendar.time)
//        _currentTime.value = currentTimeString
//
//        // Update time every second
//        Timer().scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                calendar.time = Date()
//                calendar.add(Calendar.MONTH, -1)
//                val updatedTimeString = dateFormat.format(calendar.time)
//                _currentTime.postValue(updatedTimeString)
//            }
//        }, 0, 30000)
//    }

    // 時間流動加速
    // 月份減一個月
    private fun updateTime() {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()

        // 初始化为当前时间
        var currentTime = calendar.timeInMillis

        // 更新时间的定时任务，每秒增加一小时，同时减去一个月
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (_customTime.value == true) {
                    // 增加一小时
//                currentTime += 13600000 // 3600000 毫秒为一小时

                    // 将时间戳设置到 Calendar 中
                    calendar.timeInMillis = currentTime
//                calendar.add(Calendar.MONTH, -3) // 减去一个月
                    calendar.add(Calendar.DATE, -20)

                    // 将时间戳转换为格式化的字符串
                    val updatedTimeString = dateFormat.format(calendar.time)
                    _currentTime.postValue(updatedTimeString)
                }
            }
        }, 0, 1000) // 每秒钟执行一次
    }

    // -------------------------------------------------------------------------------------------------

     val _at = MutableLiveData<String>()
    val at: LiveData<String> get() = _at

    fun setAT(value: String) {
        _at.value = value
    }

     val _ci = MutableLiveData<String>()
    val ci: LiveData<String> get() = _ci

    fun setCI(value: String) {
        _ci.value = value
    }

     val _pop6h = MutableLiveData<String>()
    val pop6h: LiveData<String> get() = _pop6h

    fun setPoP6h(value: String) {
        _pop6h.value = value
    }

     val _rh = MutableLiveData<String>()
    val rh: LiveData<String> get() = _rh

    fun setRH(value: String) {
        _rh.value = value
    }

//    private val _t = MutableLiveData<String>()
    val _t = MutableLiveData<String>()
    val t: LiveData<String> get() = _t

    fun setT(value: String) {
        _t.value = value
    }

     val _td = MutableLiveData<String>()
    val td: LiveData<String> get() = _td

    fun setTD(value: String) {
        _td.value = value
    }

     val _wd = MutableLiveData<String>()
    val wd: LiveData<String> get() = _wd

    fun setWD(value: String) {
        _wd.value = value
    }

     val _ws = MutableLiveData<String>()
    val ws: LiveData<String> get() = _ws

    fun setWS(value: String) {
        _ws.value = value
    }

     val _wx = MutableLiveData<String>()
    val wx: LiveData<String> get() = _wx

    fun setWX(value: String) {
        _wx.value = value
    }

     val _max_t = MutableLiveData<String>()
    val max_t: LiveData<String> get() = _max_t

    fun setMax_T(value: String) {
        _max_t.value = value
    }

     val _min_t = MutableLiveData<String>()
    val min_t: LiveData<String> get() = _min_t

    fun setMin_T(value: String) {
        _min_t.value = value
    }

//    private val _ = MutableLiveData<String>()
//    val : LiveData<String> get() = _
//
//    fun set(value: String) {
//        _.value = value
//    }
}
