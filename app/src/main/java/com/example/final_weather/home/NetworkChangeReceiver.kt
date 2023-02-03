package com.example.final_weather.home

import android.content.BroadcastReceiver
import android.content.Intent
import android.widget.Toast
import android.net.ConnectivityManager
import android.annotation.SuppressLint
import android.content.Context
import android.net.NetworkInfo
import java.lang.NullPointerException

class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            if (isOnline(context)) {
                Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No Network Connection", Toast.LENGTH_SHORT).show()
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            @SuppressLint("MissingPermission") val networkInfo = cm.activeNetworkInfo
            networkInfo != null && networkInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
}