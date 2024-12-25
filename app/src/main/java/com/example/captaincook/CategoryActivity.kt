package com.example.captaincook

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.captaincook.databinding.ActivityCategoryBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var cAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpRecyclerView()

    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()
        var title=intent.getStringExtra("cuisinename")
        binding.categoryTitle.text=title
        binding.rvCategory.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
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
                    if (recipe.cuisine==title) {
                        dataList.add(recipe)

                    }
                }
                cAdapter = CategoryAdapter(dataList,this@CategoryActivity)
                binding.rvCategory.adapter = cAdapter


            }

            override fun onFailure(p0: Call<mydata?>, p1: Throwable) {
                Log.d("Main Activity","on failure"+p1.message)
            }


        })
    }
}