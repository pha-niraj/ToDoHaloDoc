package com.example.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemSelectedListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    data class Todo(var title : String,var isChecked : Boolean,val date:Date)

    companion object{
         var dataArrayList= ArrayList<Todo>()
         var currentDataList = ArrayList<Todo>()
    }

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filterAdapter = ArrayAdapter<String>(
            this@MainActivity,
            android.R.layout.simple_spinner_item, arrayOf("All","Checked","Uncchecked")
        )

        val sortAdapter = ArrayAdapter<String>(
            this@MainActivity,
            android.R.layout.simple_spinner_item, arrayOf("By Date","By Alphabetical")
        )

        addData()
        recyclerViewAdapter = RecyclerViewAdapter(dataArrayList)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = recyclerViewAdapter

        filter_spinner.adapter = filterAdapter
        sort_spinner.adapter = sortAdapter

        filter_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
               currentDataList = ArrayList<Todo>()
                when (position) {
                    1 -> for (item in dataArrayList){
                        if (item.isChecked){
                            currentDataList.add(item)
                        }
                    }
                    2 -> for (item in dataArrayList){
                        if (!item.isChecked){
                            currentDataList.add(item)
                        }
                    }
                    else -> currentDataList.addAll(dataArrayList)
                }
                recyclerViewAdapter.data= currentDataList
                recyclerViewAdapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        sort_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (position==1){
                    val sortedData=currentDataList.sortedWith(Comparator { d1, d2 -> d1.title.compareTo(d2.title) })
                    val sortedArrayList = ArrayList<Todo>()
                    for (data in sortedData){
                        sortedArrayList.add(data)
                    }
                    recyclerViewAdapter.data = sortedArrayList
                    recyclerViewAdapter.notifyDataSetChanged()
                }
                else if (position==0){

                    val sortedData=currentDataList.sortedWith(Comparator { d1, d2 -> d1.date.compareTo(d2.date) })
                    val sortedArrayList = ArrayList<Todo>()
                    for (data in sortedData){
                        sortedArrayList.add(data)
                    }
                    recyclerViewAdapter.data = sortedArrayList
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun addData(){
        dataArrayList.add(Todo("Gym",true, Date(2010,11,10)))
        dataArrayList.add(Todo("Meeting",false,Date(2010,11,31)))
        dataArrayList.add(Todo("Appointment",false,Date(2010,11,22)))
        dataArrayList.add(Todo("Breakfast",false,Date(2010,11,18)))
        dataArrayList.add(Todo("Kids Pickup",true,Date(2010,11,23)))
        dataArrayList.add(Todo("Travel",false,Date(2010,11,19)))
        dataArrayList.add(Todo("Interview",true,Date(2010,11,10)))
        dataArrayList.add(Todo("Music class",false,Date(2010,11,1)))
        dataArrayList.add(Todo("Dance Class",false,Date(2010,11,5)))
    }
}
