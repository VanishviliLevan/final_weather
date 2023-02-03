package com.example.final_weather

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.final_weather.home.MyWorker
import com.example.final_weather.home.NetworkChangeReceiver
import java.security.AccessControlContext
import java.security.AccessController.getContext
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var broadcastReceiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        broadcastReceiver = NetworkChangeReceiver()
        registerNetworkBroadcastReciver()

        supportActionBar?.hide()


    }
    fun registerNetworkBroadcastReciver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(
                broadcastReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(
                broadcastReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }
    fun unregisterNetwork() {
        try {
            unregisterReceiver(broadcastReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetwork()
    }
}


