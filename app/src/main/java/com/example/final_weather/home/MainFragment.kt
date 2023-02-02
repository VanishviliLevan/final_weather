package com.example.final_weather.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.final_weather.R

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewPager)

        val fragmentList = arrayListOf<Fragment>(
            HomeFragment(),
            FormFragment()

        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        viewpager.adapter = adapter


        return view
    }

}