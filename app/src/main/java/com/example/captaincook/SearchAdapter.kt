package com.example.captaincook

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.captaincook.databinding.SearchRvBinding
import com.squareup.picasso.Picasso


class SearchAdapter(var context: SearchActivity, var dataList: ArrayList<Recipe>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
     inner class ViewHolder(var binding: SearchRvBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= SearchRvBinding.inflate(android.view.LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<Recipe>) {
        dataList = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.searchText.text=dataList[position].name
        Picasso.get().load(dataList[position].image).into(holder.binding.imageSearch);
        holder.itemView.setOnClickListener()
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

