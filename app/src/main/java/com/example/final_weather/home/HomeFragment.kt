package com.example.final_weather.home

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.final_weather.R
import com.example.final_weather.api.RetrofitClient
import com.example.final_weather.dialog.CustomDialogFragment
import com.example.final_weather.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment(private val cityName:String): Fragment() {
    private lateinit var txt:TextView
    private lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        txt = view.findViewById(R.id.textView5)
        btn = view.findViewById(R.id.button)



        val k:String = "355cbfa67d7e4fac866230344230102"
        RetrofitClient.instance.getWeather(k,cityName).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val temp = response.body()?.current

                Log.e("CityName",cityName)
                if(response.code() == 400){
                    Toast.makeText(context,"Location Name is Incorrect",Toast.LENGTH_LONG).show()
                }
                else {
                if (temp != null){
                    val numb = temp.currentC.toInt()
                    txt.text = numb.toString()
                }


                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Detail User", t.message.toString())
                Toast.makeText(context,"Location Name is Incorrect",Toast.LENGTH_LONG).show()
            }

        })

        btn.setOnClickListener {


            val exampleDialog = CustomDialogFragment()
            exampleDialog.show(childFragmentManager, "example_dialog")








            }



    }

}