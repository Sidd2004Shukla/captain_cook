package com.example.captaincook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.captaincook.databinding.ActivityRecipeBinding
import com.squareup.picasso.Picasso

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.detailst.text=intent.getStringExtra("preparation")
        binding.cooking.text=intent.getStringExtra("cooking")
        binding.recipeNamed.text=intent.getStringExtra("name")
        binding.ing.text=intent.getStringExtra("ingredients")
        binding.steps.text=intent.getStringExtra("steps")
        Picasso.get().load(intent.getStringExtra("image")).into(binding.recipeImg)
    }
}