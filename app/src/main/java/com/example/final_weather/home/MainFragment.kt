package com.example.final_weather.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.final_weather.R


class MainFragment : Fragment() {

    private lateinit var cityArg:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewPager)

        if (arguments?.getString("amount") != null){
            cityArg = arguments?.getString("amount").toString()
        } else{
            cityArg = "tbilisi"
        }

        val fragmentList = arrayListOf<Fragment>(
            HomeFragment(cityArg),
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