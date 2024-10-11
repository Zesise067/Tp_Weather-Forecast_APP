package com.example.applicationtestsuite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NestedPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NestedFirstFragment()
            else -> NestedSecondFragment()
        }
    }

    override fun getCount(): Int {
        return 2 // Number of nested pages
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Nested Page 1"
            else -> "Nested Page 2"
        }
    }
}
