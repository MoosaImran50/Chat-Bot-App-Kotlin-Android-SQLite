package com.example.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyMessageRecyclerViewAdapter(private val messagesList: MutableList<Message>) : RecyclerView.Adapter<MyViewHolder1>() {

    companion object {
        private const val LEFT_MESSAGE = 1
        private const val RIGHT_MESSAGE = 2
    }

    override fun getItemViewType(position: Int): Int {
        val message = messagesList[position]
        if (message.name == "Me") {
            return RIGHT_MESSAGE
        }
        else {
            return LEFT_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View
        if (viewType == LEFT_MESSAGE){
            listItem = layoutInflater.inflate(R.layout.left_list_item, parent, false)
        }
        else {
            listItem = layoutInflater.inflate(R.layout.right_list_item, parent, false)
        }
        return  MyViewHolder1(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {
        val message = messagesList[position]
        if (message.name == "Me"){
            holder.bindRight(message)
        }
        else {
            holder.bindLeft(message)
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

}

class MyViewHolder1(private val view: View):RecyclerView.ViewHolder(view){
    fun bindLeft(messageText: Message){
        val userName = view.findViewById<TextView>(R.id.tvUserNameLeft)
        val userMessage = view.findViewById<TextView>(R.id.tvMessageLeft)
        val userTimeStamp = view.findViewById<TextView>(R.id.tvTimeStampLeft)

        userName.text = messageText.name
        userMessage.text = messageText.message
        userTimeStamp.text = messageText.time_stamp
    }

    fun bindRight(messageText: Message){
        val userName = view.findViewById<TextView>(R.id.tvUserNameRight)
        val userMessage = view.findViewById<TextView>(R.id.tvMessageRight)
        val userTimeStamp = view.findViewById<TextView>(R.id.tvTimeStampRight)

        userName.text = messageText.name
        userMessage.text = messageText.message
        userTimeStamp.text = messageText.time_stamp
    }

}