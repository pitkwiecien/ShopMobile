package com.example.testapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

//import com.example.testapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "172.31.108.220/api/users/"
        val queue = Volley.newRequestQueue(this)

        val req = StringRequest(
            Request.Method.GET, url, {
                response -> Log.e("sth", response)
            }, {  }
        )

        queue.add(req)

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