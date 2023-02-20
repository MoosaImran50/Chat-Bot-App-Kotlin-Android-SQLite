package com.example.assignment1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val carsList = listOf< Car>(Car("Civic", "Honda"),
        Car("Corolla", "Toyota"),
        Car("Sportage", "KIA"),
        Car("Swift", "Suzuki"),
        Car("City", "Honda"),
        Car("Cultus", "Suzuki"),
        Car("Alto", "Suzuki"),
        Car("Fortuner", "Toyota"),
        Car("Revo", "Toyota"),
        Car("Land Cruiser", "Toyota"),
        Car("Accord", "Honda"),
        Car("Picanto", "KIA"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.BLUE)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(carsList)
    }
}