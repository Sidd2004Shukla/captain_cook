package com.example.captaincook

import retrofit2.Call
import retrofit2.http.GET

interface apiinterface {
    @GET("recipes")
    fun getproducts(): Call<mydata>


}
