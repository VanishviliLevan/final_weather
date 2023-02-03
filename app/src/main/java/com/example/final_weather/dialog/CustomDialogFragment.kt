package com.example.final_weather.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.final_weather.R


class CustomDialogFragment:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView:View = inflater.inflate(R.layout.dialog_main,container,false)

        return rootView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = view.findViewById<EditText>(R.id.city_name)
        val search = view.findViewById<Button>(R.id.search_button)


        search.setOnClickListener {

            val bundle = bundleOf("amount" to cityName.text.toString())
            findNavController().navigate(R.id.mainFragment, bundle)

            dismiss()










        }


    }

}