package com.example.applicationtestsuite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val pageCount = 2 // 假设有两个分页

    override fun getItemCount(): Int {
        // 返回一个足够大的值，以实现分页循环
        return Int.MAX_VALUE
    }

    override fun createFragment(position: Int): Fragment {
        // 使用取模运算符获取正确的位置，以确保分页循环
        val index = position % pageCount
        return when (index) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
