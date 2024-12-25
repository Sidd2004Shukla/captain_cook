package com.example.captaincook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.captaincook.databinding.PopularRvBinding
import com.squareup.picasso.Picasso

class PopularAdapter( var dataList: ArrayList<Recipe>,  var  context: Context) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:PopularRvBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= PopularRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.popularText.text= dataList[position].name
        Picasso.get().load(dataList[position].image).into(holder.binding.popularImage);
        holder.binding.popularText2.text=dataList[position].prepTimeMinutes.toString()+"mins"
        holder.itemView.setOnClickListener{
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