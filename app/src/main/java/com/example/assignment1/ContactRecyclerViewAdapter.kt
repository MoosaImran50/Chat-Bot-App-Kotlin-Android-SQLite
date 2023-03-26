package com.example.assignment1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyContactRecyclerViewAdapter(private val contactsList: MutableList<Contact>) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.MyViewHolder2>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.contact_item, parent, false)

        return  MyViewHolder2(listItem, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val contact = contactsList[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }


    class MyViewHolder2(private val view: View, listener: OnItemClickListener): RecyclerView.ViewHolder(view){
        init{
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(contactText: Contact){
            val nameText = view.findViewById<TextView>(R.id.contactName)

            nameText.text = contactText.name
        }

    }

}