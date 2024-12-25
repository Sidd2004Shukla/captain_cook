package com.example.captaincook

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.captaincook.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rAdapter: SearchAdapter
    private lateinit var reterofitData: Call<mydata>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.searchTab.requestFocus()
        dataList = ArrayList()
        rAdapter= SearchAdapter(this@SearchActivity,dataList)
        binding.rvSearch.adapter = rAdapter
        val reteroFit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiinterface::class.java)
         reterofitData = reteroFit.getproducts()
        setUpRecyclerView()
        binding.searchTab.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString()!=""){
                    filterdetails(p0.toString())

                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }

    private fun filterdetails(filereddata: String) {
        var filterData= ArrayList<Recipe>()
        for(item in dataList) {
            if (item.name.lowercase().contains(filereddata.lowercase())) {
                filterData.add(item)
            }
            if(filereddata.length==0)
            {
                filterData=dataList
            }
        }
        rAdapter.filterList(filterlist=filterData)
    }

    private fun setUpRecyclerView() {

        binding.rvSearch.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        reterofitData.enqueue(object : retrofit2.Callback<mydata?> {
            override fun onResponse(p0: Call<mydata?>, p1: Response<mydata?>) {
                var responseBody = p1.body()
                val productList = responseBody?.recipes!!
                for (recipe in productList) {

                        dataList.add(recipe)


                }
                 rAdapter= SearchAdapter(this@SearchActivity,dataList)
                binding.rvSearch.adapter = rAdapter


            }

            override fun onFailure(p0: Call<mydata?>, p1: Throwable) {
                Log.d("Main Activity","on failure"+p1.message)
            }


        })
    }
}