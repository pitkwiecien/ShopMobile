package com.example.testapp1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testapp1.databinding.ActivityMainBinding
import org.json.JSONArray


class UsersActivity : AppCompatActivity() {
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
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }

        binding.aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        showDataFromApi(URL)

        this.deleteDatabase("UserDB")

        val dbHelper = DBHelper(this)
        dbHelper.insert("user", arrayListOf(
            mapOf(
                "id" to "_1",
                "name" to "pablo",
                "surname" to "kowalski"
            ),
            mapOf(
                "id" to "_2",
                "name" to "piotr",
                "surname" to "kox"
            )
        ))

        Log.e("dbUsers", dbHelper.getContent().toString())

        dbHelper.close()
    }

    fun showDataFromApi(fromUrl: String){
        val req = StringRequest(
            Request.Method.GET, "$fromUrl/", {
                    response -> Log.d("test1", response)
                val jsonArray = JSONArray(response.toString())
                val resultList: ArrayList<ArrayList<String>> = ArrayList()
                for (i in 0 until jsonArray.length()){
                    val tmpList: ArrayList<String> = ArrayList()
                    tmpList.add(jsonArray.getJSONObject(i).getString("id").toString())
                    tmpList.add(jsonArray.getJSONObject(i).getString("name"))
                    tmpList.add(jsonArray.getJSONObject(i).getString("surname"))
                    resultList.add(tmpList)
                }
                binding.recycler.adapter = Adapter(resultList, this)
            }, {
                    response -> Log.e("test1", response.toString())
            }
        )
        val queue = Volley.newRequestQueue(this)
        queue.add(req)
    }

    fun delUser(fromUrl: String, userId: Int){
        val newUrl = "$fromUrl/$userId"
        val req = StringRequest(
            Request.Method.DELETE, newUrl, { showDataFromApi(fromUrl) }, { }
        )
        val queue = Volley.newRequestQueue(this)
        queue.add(req)
    }

    companion object{
        const val URL = Statics.USED_URL + "/users"
    }
}