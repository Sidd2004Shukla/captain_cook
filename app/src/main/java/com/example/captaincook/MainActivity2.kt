package com.example.captaincook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.captaincook.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>

    private lateinit var cuAdapter:CuisineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpRecyclerView()
        /*
        binding.salad.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }

         */
        binding.search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))

        }
        val recdata=ArrayList<data>()
        val cuisine = arrayOf(
            "Italian",
            "Asian",
            "American",
            "Mexican",
            "Mediterranean",
            "Pakistani",
            "Japanese",
            "Moroccan",
            "Korean",
            "Greek",
            "Thai",
            "Indian",
            "Turkish",
            "Smoothie",
            "Russian",
            "Lebanese",
            "Brazilian"
        )
        val image = arrayOf(
            "https://cdn.dummyjson.com/recipe-images/1.webp",
            "https://cdn.dummyjson.com/recipe-images/2.webp",
            "https://cdn.dummyjson.com/recipe-images/3.webp",
            "https://cdn.dummyjson.com/recipe-images/5.webp",
            "https://cdn.dummyjson.com/recipe-images/6.webp",
            "https://cdn.dummyjson.com/recipe-images/11.webp",
            "https://cdn.dummyjson.com/recipe-images/16.webp",
            "https://cdn.dummyjson.com/recipe-images/17.webp",
            "https://cdn.dummyjson.com/recipe-images/18.webp",
            "https://cdn.dummyjson.com/recipe-images/19.webp",
            "https://cdn.dummyjson.com/recipe-images/21.webp",
            "https://cdn.dummyjson.com/recipe-images/22.webp",
            "https://cdn.dummyjson.com/recipe-images/24.webp",
            "https://cdn.dummyjson.com/recipe-images/25.webp",
            "https://cdn.dummyjson.com/recipe-images/27.webp",
            "https://cdn.dummyjson.com/recipe-images/29.webp",
            "https://cdn.dummyjson.com/recipe-images/30.webp"
        )
        for (i in cuisine.indices)
        {
            var newdata=data(image[i],cuisine[i])
            recdata.add(newdata)

        }
        binding.cuisine.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.cuisine.isClickable=true
        cuAdapter = CuisineAdapter(recdata,this)
        binding.cuisine.adapter = cuAdapter
        cuAdapter.setonitemclick(object : recyclerview {
            override fun onclickListerner(position: Int) {
               var myintent= Intent(this@MainActivity2, CategoryActivity::class.java)
                myintent.putExtra("cuisinename",recdata[position].name)
                startActivity(myintent)


            }


        })

    }


    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.popularRv.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val reteroFit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiinterface::class.java)
        val reterofitData = reteroFit.getproducts()
        reterofitData.enqueue(object : retrofit2.Callback<mydata?> {
            override fun onResponse(p0: Call<mydata?>, p1: Response<mydata?>) {
                var responseBody = p1.body()
                val productList = responseBody?.recipes!!
                for (recipe in productList) {
                    if (recipe.rating > 4) {
                        dataList.add(recipe)

                    }
                }
                rvAdapter = PopularAdapter(dataList,this@MainActivity2)
                binding.popularRv.adapter = rvAdapter


            }

            override fun onFailure(p0: Call<mydata?>, p1: Throwable) {
                Log.d("Main Activity","on failure"+p1.message)
            }


        })
    }
}