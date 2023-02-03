package com.example.final_weather.home

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.final_weather.R
import com.example.final_weather.api.RetrofitClient
import com.example.final_weather.dialog.CustomDialogFragment
import com.example.final_weather.models.WeatherResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment(private val cityName:String): Fragment() {
    private lateinit var temperature:TextView
    private lateinit var country:TextView
    private lateinit var region:TextView
    private lateinit var condition:TextView
    private lateinit var city:TextView
    private lateinit var icon:ImageView
    private lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        country = view.findViewById(R.id.countryName)
        region = view.findViewById(R.id.regionName)
        condition = view.findViewById(R.id.condition)
        city = view.findViewById(R.id.cityName)
        temperature = view.findViewById(R.id.temperature)
        icon = view.findViewById(R.id.icon)
        btn = view.findViewById(R.id.button)



        val k:String = "355cbfa67d7e4fac866230344230102"
        RetrofitClient.instance.getWeather(k,cityName).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val data = response.body()

                Log.e("CityName",cityName)
                if(response.code() == 400){
                    Toast.makeText(context,"Location Name is Incorrect",Toast.LENGTH_LONG).show()
                }
                else {
                if (data != null){
                    val numb = data.current.temp.toInt()
                    temperature.text = numb.toString()
                    condition.text = data.current.condition.text
                    region.text = data.location.region
                    country.text = data.location.country
                    city.text = data.location.name

                    Picasso.get().load("https:"+data.current.condition.icon).into(icon)

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