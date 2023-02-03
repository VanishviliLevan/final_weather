package com.example.final_weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.final_weather.home.MyWorker


class WelcomeFragment : Fragment() {


    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        Handler().postDelayed({
        findNavController().navigate(R.id.action_welcomeFragment_to_mainFragment)
        }, 3000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

}