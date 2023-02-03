package com.example.final_weather.home

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_weather.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FormFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    var addbutton: FloatingActionButton? = null
    var databaseHelper: DatabaseHelper? = null
    var adapter: MainAdapter? = null
    lateinit var title: TextView
    lateinit var description: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        addbutton = view.findViewById(R.id.add_button)

        databaseHelper = DatabaseHelper(context)

        recyclerView!!.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(activity as AppCompatActivity, databaseHelper!!.getArray())
        recyclerView!!.setAdapter(adapter)

        addbutton!!.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(activity as Context)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.dialog_main1)
            dialog.show()

            val titleedittext = dialog.findViewById<EditText>(R.id.title_edittext)
            val descriptionedittext = dialog.findViewById<EditText>(R.id.description_edittext)
            val submitbutton = dialog.findViewById<Button>(R.id.submit_button)

            submitbutton.setOnClickListener {
                val sTitle = titleedittext.text.toString().trim { it <= ' ' }
                val sDescription = descriptionedittext.text.toString().trim { it <= ' ' }

                databaseHelper!!.insert(sTitle, sDescription)
                adapter!!.updateArray(databaseHelper!!.getArray())
                this.title = titleedittext
                this.description = descriptionedittext

                dialog.dismiss()
            }
        })

    }

}