package com.example.testapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
//import com.example.testapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = this.findViewById<RecyclerView>(R.id.recycler)
        val arr = arrayOf(
            arrayOf("1", "Antoni", "Dzwoni"),
            arrayOf("2", "Michaś", "Bagietka"),
            arrayOf("3", "Maks", "Grzyb")
        )
        println(arr.toString())
        recycler.adapter = Adapter(arr)
    }
}