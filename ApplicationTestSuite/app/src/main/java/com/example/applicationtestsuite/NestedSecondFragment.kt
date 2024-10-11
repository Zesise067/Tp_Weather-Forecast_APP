package com.example.applicationtestsuite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class NestedSecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nested_second, container, false)

        val nestedViewPager: ViewPager = view.findViewById(R.id.nestedViewPager)
        val nestedAdapter = _NestedPagerAdapter(childFragmentManager)
        nestedViewPager.adapter = nestedAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(nestedViewPager)

        return view
    }
}
