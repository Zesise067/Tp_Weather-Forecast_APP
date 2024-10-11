package com.example.applicationtestsuite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class _NestedPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> _NestedFirstFragment()
            1 -> _NestedSecondFragment()
            else -> _NestedThirdFragment()
        }
    }

    override fun getCount(): Int {
        return 3 // Number of nested pages
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "今天"
            1 -> "明天"
            else -> "後天"
        }
    }
}
