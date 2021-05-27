package com.example.a7minuteworkoutapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkoutapplication.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val item: ArrayList<ExerciseModel>, val context: Context): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = item[position]

        holder.tvItem.text = model.getId().toString()

        if (model.getIsSelected()){
            holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_border)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        } else if (model.getIsCompleted()){
            holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_background)
            holder.tvItem.setTextColor(Color.WHITE)

        } else{
            holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_grey_background)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}