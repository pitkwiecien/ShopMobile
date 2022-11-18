package com.example.testapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productsButton.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        binding.usersButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        val url = "192.168.1.118:8080/api/users/"
        val queue = Volley.newRequestQueue(this)

        val req = StringRequest(
            Request.Method.GET, url, {
                response -> Log.e("sth", response)
            }, {  }
        )

        queue.add(req)

        val arr = arrayOf(
            arrayOf("1", "Antoni", "Dzwoni"),
            arrayOf("2", "Michaś", "Bagietka"),
            arrayOf("3", "Maks", "Grzyb")
        )
        println(arr.toString())
        binding.recycler.adapter = Adapter(arr)
    }
}