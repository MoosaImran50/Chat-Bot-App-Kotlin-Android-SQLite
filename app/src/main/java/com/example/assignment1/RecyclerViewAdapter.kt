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
        val userName = view.findViewById<TextView>(R.id.userName)
        val userMessage = view.findViewById<TextView>(R.id.tvName)
        val userTimeStamp = view.findViewById<TextView>(R.id.timeStamp)

        userName.text = messageText.name
        userMessage.text = messageText.message
        userTimeStamp.text = messageText.time_stamp
    }

}