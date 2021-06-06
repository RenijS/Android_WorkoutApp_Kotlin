package com.example.a7minuteworkoutapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkoutapplication.databinding.ItemExerciseStatusBinding
import com.example.a7minuteworkoutapplication.databinding.ItemHistoryRowBinding
import java.util.ArrayList

class HistoryAdapter(val context: Context,val item: ArrayList<MyDataClass>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        val llMainRow = binding.llHistoryMain
        val tvId = binding.tvId
        val tvDate = binding.tvDate
        val tvMood = binding.ivMood
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String = item[position].date
        val img: Int = item[position].img
        Log.e("HistoryAdapter", ""+date)
        holder.tvId.text = (position+1).toString()
        holder.tvDate.text = date
        holder.tvMood.setImageResource(img)
    }

    override fun getItemCount(): Int {
        return item.size
    }
}