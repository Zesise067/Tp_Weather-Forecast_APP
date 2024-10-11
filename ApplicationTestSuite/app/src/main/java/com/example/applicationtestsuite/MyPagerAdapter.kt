package com.example.applicationtestsuite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
//            1 -> SecondFragment()
//            else -> ThirdFragment()

            else -> SecondFragment()
        }
    }

    override fun getCount(): Int {
//        return 3 // Number of main pages

        return 2 // Number of main pages
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Main Page 1"
//            1 -> "Main Page 2"
//            else -> "Main Page 3"

            else -> "Main Page 2"
        }
    }
}

// -------------------------------------------------------------------------------------------------------

// 備份

//package com.example.applicationtestsuite
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//
//class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> FirstFragment()
//            1 -> SecondFragment()
//            else -> ThirdFragment()
//        }
//    }
//
//    override fun getCount(): Int {
//        return 3 // Number of main pages
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return when (position) {
//            0 -> "Main Page 1"
//            1 -> "Main Page 2"
//            else -> "Main Page 3"
//        }
//    }
//}
