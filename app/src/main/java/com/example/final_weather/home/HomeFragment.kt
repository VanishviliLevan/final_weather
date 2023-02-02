package com.example.final_weather.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.final_weather.R
import com.example.final_weather.api.RetrofitClient
import com.example.final_weather.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txt = view.findViewById<TextView>(R.id.textView2)
        val k:String = "355cbfa67d7e4fac866230344230102"
        RetrofitClient.instance.getWeather("355cbfa67d7e4fac866230344230102","London").enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val temp = response.body()?.current

                if (temp != null){
                    txt.text = temp.currentC.toString()



                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Detail User", t.message.toString())
            }

        })

    }

}