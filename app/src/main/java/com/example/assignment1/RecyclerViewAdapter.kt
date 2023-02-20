package com.example.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(private val carsList: List<Car>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return  MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val car = carsList[position]
        holder.bind(car)
    }

    override fun getItemCount(): Int {
        return carsList.size
    }

}

class MyViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(car: Car){
        val myTextView = view.findViewById<TextView>(R.id.tvName)
        myTextView.text = car.name
    }

}