package com.example.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(private val messagesList: MutableList<Message>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return  MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message = messagesList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

}

class MyViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(messageText: Message){
        val myTextView = view.findViewById<TextView>(R.id.tvName)
        myTextView.text = messageText.name + "\n\n" + messageText.message
    }

}