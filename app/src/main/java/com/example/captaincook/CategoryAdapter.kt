package com.example.captaincook


import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.captaincook.databinding.CategoryRvBinding
import com.squareup.picasso.Picasso

import java.util.ArrayList

class CategoryAdapter(var dataList: ArrayList<Recipe>, var context: CategoryActivity): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(var binding: CategoryRvBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     var view = CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textCategory.text = dataList[position].name
        holder.binding.timeCategory.text = dataList[position].prepTimeMinutes.toString()+"mins"
        Picasso.get().load(dataList[position].image).into(holder.binding.categoryImage)
        holder.binding.backbtnCategory.setOnClickListener()
        {
            var myintent= Intent(context,RecipeActivity::class.java)
            var stringing=""
            var stringins=""
            var i=1
            var k=1
            for (s in dataList[position].ingredients)
            {
                stringing=stringing+k.toString()+"."+"->"+" "+s+"\n"
                k++

            }
            for(s in dataList[position].instructions)
            {
                stringins=stringins+i.toString()+"."+"->"+" "+s+"\n"

                i++
            }
            myintent.putExtra("preparation",dataList[position].prepTimeMinutes.toString()+"mins")
            myintent.putExtra("cooking",dataList[position].cookTimeMinutes.toString()+"mins")
            myintent.putExtra("ingredients",stringing)
            myintent.putExtra("name",dataList[position].name)
            myintent.putExtra("steps",stringins)
            myintent.putExtra("image",dataList[position].image)
            context.startActivity(myintent)
        }
    }

}
