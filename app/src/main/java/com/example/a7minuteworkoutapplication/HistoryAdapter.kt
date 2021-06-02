package com.example.a7minuteworkoutapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkoutapplication.databinding.ItemExerciseStatusBinding
import com.example.a7minuteworkoutapplication.databinding.ItemHistoryRowBinding
import java.util.ArrayList

class HistoryAdapter(val context: Context,val item: ArrayList<String>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        val llMainRow = binding.llHistoryMain
        val tvId = binding.tvId
        val tvDate = binding.tvDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String = item[position]
        Log.e("HistoryAdapter", ""+date)
        holder.tvId.text = (position+1).toString()
        holder.tvDate.text = date
    }

    override fun getItemCount(): Int {
        return item.size
    }
}