package com.example.captaincook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.captaincook.databinding.EachcuisineBinding
import com.squareup.picasso.Picasso
import java.util.ArrayList
private lateinit var mlistener:recyclerview
class CuisineAdapter(var recdata: ArrayList<data>, var context: MainActivity2): RecyclerView.Adapter<CuisineAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: EachcuisineBinding,listener:recyclerview): RecyclerView.ViewHolder(binding.root) {
      init {
          itemView.setOnClickListener()
          {
              listener.onclickListerner(position = adapterPosition)
          }
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = EachcuisineBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view, mlistener)
    }

    override fun getItemCount(): Int {
        return recdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current=recdata[position]
        holder.binding.textCuisine.text = current.name
        Picasso.get().load(current.image).into(holder.binding.imgCuisine)
    }
    fun setonitemclick(listener:recyclerview)
    {
        mlistener=listener
    }

}
