package com.example.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerViewAdapter(var data : ArrayList<MainActivity.Todo>,context : Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {

        viewHolder.titleView.text = data[position].title
        viewHolder.checkBox.isChecked = data[position].isChecked
        viewHolder.checkBox.setOnClickListener {
            val isCheckBoxChecked = viewHolder.checkBox.isChecked
            MainActivity.dataArrayList[position].isChecked = isCheckBoxChecked
        }

        viewHolder.deleteView.setOnClickListener {
            MainActivity.dataArrayList.remove(data[position])
            data.removeAt(position)
           notifyDataSetChanged()
            Log.d("DataSize",data.size.toString())
            Log.d("RecylerVIew Position",position.toString())
        }
    }

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val titleView = view.textView
        val checkBox= view.checkbox
        val deleteView = view.delete_image_view

    }
}