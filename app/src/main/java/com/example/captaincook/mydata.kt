package com.example.captaincook

data class mydata(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)