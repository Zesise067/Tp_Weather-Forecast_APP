//package com.example.applicationtestsuite
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.google.android.material.tabs.TabLayout
//import androidx.viewpager.widget.ViewPager
//
//class FirstFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_first, container, false)
//
//        val nestedViewPager: ViewPager = view.findViewById(R.id.nestedViewPager)
////        val nestedTabLayout: TabLayout = view.findViewById(R.id.nestedTabLayout)
//        val nestedAdapter = NestedPagerAdapter(childFragmentManager)
//        nestedViewPager.adapter = nestedAdapter
//
////        nestedTabLayout.setupWithViewPager(nestedViewPager)
//
//        return view
//    }
//}

//package com.example.applicationtestsuite
//
//import android.animation.ObjectAnimator
//import android.os.Bundle
//import android.text.Html
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.viewpager.widget.ViewPager
//import androidx.viewpager2.widget.ViewPager2
//import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayoutMediator
//
//class FirstFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_first, container, false)
//
//        val textView: TextView = view.findViewById(R.id.textView3)
//        val text = "This is <b>bold</b> text."
//        textView.text = Html.fromHtml(text)
//
//        val textView4: TextView = view.findViewById(R.id.textView4)
//        val text4 = "<b>28\u00B0</b>"
//        textView4.text = Html.fromHtml(text4)
//
//        val textView6: TextView = view.findViewById(R.id.textView6)
//        val text6 = "體感溫度 <b>34\u00B0C</b>"
//        textView6.text = Html.fromHtml(text6)
//
//
//
//        val nestedViewPager: ViewPager = view.findViewById(R.id.nestedViewPager)
////        val nestedTabLayout: TabLayout = view.findViewById(R.id.nestedTabLayout)
//        val nestedAdapter = NestedPagerAdapter(childFragmentManager)
//        nestedViewPager.adapter = nestedAdapter
//
////        TabLayoutMediator(nestedTabLayout, nestedViewPager) { tab, position ->
////            tab.text = when (position) {
////                0 -> "Nested First"
////                1 -> "Nested Second"
////                else -> null
////            }
////        }.attach()
//
//        // 找到 ImageView 并应用旋转动画
//        val headerImageView: ImageView = view.findViewById(R.id.headerImageView)
//        startRotationAnimation(headerImageView)
//
//        return view
//    }
//
//    private fun startRotationAnimation(imageView: ImageView) {
//        val rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
//        rotationAnimator.duration = 10000 // 动画持续时间
//        rotationAnimator.repeatCount = ObjectAnimator.INFINITE // 无限循环
//        rotationAnimator.start()
//    }
//}


package com.example.applicationtestsuite

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class FirstFragment : Fragment() {

//    private var _at: String = ""
//    private var _ci: String = ""
//    private var _pop6h: String = ""
//    private var _rh: String = ""
//    private var _t: String = ""
//    private var _td: String = ""
//    private var _wd: String = ""
//    private var _ws: String = ""
//    private var _wx: String = ""
//    private var _max_t: String = ""
//    private var _min_t: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val textView6: TextView = view.findViewById(R.id.textView6)
        DataHolder.at.observe(viewLifecycleOwner, Observer { at ->
            val finalText6 = "體感溫度 $at\u00B0"
            textView6.text = Html.fromHtml(finalText6)
        })

        DataHolder.ci.observe(viewLifecycleOwner, Observer { ci ->
        })

        DataHolder.pop6h.observe(viewLifecycleOwner, Observer { pop6h ->
        })

        DataHolder.rh.observe(viewLifecycleOwner, Observer { rh ->
        })

        val textView4: TextView = view.findViewById(R.id.textView4)
        DataHolder.t.observe(viewLifecycleOwner, Observer { t ->
            val finalText4 = "$t\u00B0"
            textView4.text = Html.fromHtml(finalText4)
        })

        DataHolder.td.observe(viewLifecycleOwner, Observer { td ->
        })

        val textView888: TextView = view.findViewById(R.id.textView888)
        DataHolder.wd.observe(viewLifecycleOwner, Observer { wd ->
            textView888.text = Html.fromHtml(wd)
        })

        val textView8868: TextView = view.findViewById(R.id.textView8868)
        DataHolder.ws.observe(viewLifecycleOwner, Observer { ws ->
            val finalText8868 = "$ws km/h"
            textView8868.text = Html.fromHtml(finalText8868)
        })

        val imageView34: ImageView = view.findViewById(R.id.imageView34)
        DataHolder.wx.observe(viewLifecycleOwner, Observer { wx ->
            if (wx == "陰") {
                imageView34.setImageResource(R.drawable.cloudyday)
            }else if(wx == "晴"){
                imageView34.setImageResource(R.drawable.clear)
            }else if(wx == "短暫雨" || wx == "短暫陣雨"){
                imageView34.setImageResource(R.drawable.rain)
            }else if(wx == "短暫陣雨或雷雨" || wx == "陣雨或雷雨"){
                imageView34.setImageResource(R.drawable.thunderstorm)
            }else if(wx == "短暫陣雨有霧"){
                imageView34.setImageResource(R.drawable.fog)
            }else if(wx == "午後短暫陣雨" || wx == "午後短暫雷陣雨"){
                imageView34.setImageResource(R.drawable.briefshowers)
            }else{
                imageView34.setImageResource(R.drawable.partlycloudy)
            }
        })

        val textView881: TextView = view.findViewById(R.id.textView881)
        DataHolder.max_t.observe(viewLifecycleOwner, Observer { max_t ->
            val finalText881 = "$max_t\u00B0C"
            textView881.text = Html.fromHtml(finalText881)
        })

        val textView880: TextView = view.findViewById(R.id.textView880)
        DataHolder.min_t.observe(viewLifecycleOwner, Observer { min_t ->
            val finalText880 = "$min_t\u00B0C"
            textView880.text = Html.fromHtml(finalText880)
        })

        val nestedViewPager: ViewPager = view.findViewById(R.id.nestedViewPager)
        val nestedAdapter = NestedPagerAdapter(childFragmentManager)
        nestedViewPager.adapter = nestedAdapter

        // Apply rotation animation to ImageView
        val headerImageView: ImageView = view.findViewById(R.id.headerImageView)
        startRotationAnimation(headerImageView, false)

        val headerImageView2: ImageView = view.findViewById(R.id.headerImageView2)
        startRotationAnimation(headerImageView2, true)

        return view
    }

    private fun startRotationAnimation(imageView: ImageView, isClockwise: Boolean) {
        val rotationAnimator: ObjectAnimator
        if (isClockwise) {
            rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 1440f, 0f)
        } else {
            rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 720f)
        }

        rotationAnimator.duration = 80000 // 動畫持續時間
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE // 無限次循環

        rotationAnimator.start()
    }
}
