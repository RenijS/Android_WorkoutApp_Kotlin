package com.example.a7minuteworkoutapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.a7minuteworkoutapplication.databinding.ActivityMoodBinding

class MoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoodBinding

    private var currentlySelected: ImageView? = null
    private var previouslySelected: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            buttonEnter.setOnClickListener {
                if (currentlySelected != null){
                    //savingMoodToDatabase()
                    finish()
                    val intent = Intent(this@MoodActivity, FinalScreenActivity::class.java)
                    when(currentlySelected){
                        binding.ivMood0 -> intent.putExtra("Selected", R.drawable.ic_mood_0)
                        binding.ivMood1 -> intent.putExtra("Selected", R.drawable.ic_mood_1)
                        binding.ivMood2 -> intent.putExtra("Selected", R.drawable.ic_mood_2)
                        binding.ivMood3 -> intent.putExtra("Selected", R.drawable.ic_mood_3)
                        binding.ivMood4 -> intent.putExtra("Selected", R.drawable.ic_mood_4)
                        binding.ivMood5 -> intent.putExtra("Selected", R.drawable.ic_mood_5)
                    }
                    startActivity(intent)
                } else{
                    Toast.makeText(this@MoodActivity, "Choose something", Toast.LENGTH_SHORT).show()
                }
            }
            ivMood0.setOnClickListener {
                if (currentlySelected != null){
                    previouslySelected = currentlySelected
                    currentlySelected = ivMood0
                } else{
                    currentlySelected = ivMood0
                }
                whenImageSelected()
            }

            ivMood1.setOnClickListener {
                if (currentlySelected != null){
                    previouslySelected = currentlySelected
                    currentlySelected = ivMood1
                } else{
                    currentlySelected = ivMood1
                }
                whenImageSelected()
            }

            ivMood2.setOnClickListener {
                if (currentlySelected != null){
                    previouslySelected = currentlySelected
                    currentlySelected = ivMood2
                } else{
                    currentlySelected = ivMood2
                }
                whenImageSelected()
            }

            ivMood3.setOnClickListener {
                if (currentlySelected != null){
                    previouslySelected = currentlySelected
                    currentlySelected = ivMood3
                } else{
                    currentlySelected = ivMood3
                }
                whenImageSelected()
            }

            ivMood4.setOnClickListener {
                if (currentlySelected != null){
                    previouslySelected = currentlySelected
                    currentlySelected = ivMood4
                } else{
                    currentlySelected = ivMood4
                }
                whenImageSelected()
            }

            ivMood5.setOnClickListener {
                if (currentlySelected != null){
                    previouslySelected = currentlySelected
                    currentlySelected = ivMood5
                } else{
                    currentlySelected = ivMood5
                }
                whenImageSelected()
            }
        }
    }

    fun whenImageSelected(){
        if (currentlySelected != null){
            if (previouslySelected == null){
                currentlySelected!!.setBackgroundColor(ContextCompat.getColor(this@MoodActivity, R.color.button_selected))
            } else{
                previouslySelected!!.setBackgroundColor(Color.WHITE)
                currentlySelected!!.setBackgroundColor(ContextCompat.getColor(this@MoodActivity, R.color.button_selected))
            }
        }
    }

    fun savingMoodToDatabase(){
        var img : Int? = null
        when(currentlySelected){
            binding.ivMood0 -> img = R.drawable.ic_mood_0
            binding.ivMood1 -> img = R.drawable.ic_mood_1
            binding.ivMood2 -> img = R.drawable.ic_mood_2
            binding.ivMood3 -> img = R.drawable.ic_mood_3
            binding.ivMood4 -> img = R.drawable.ic_mood_4
            binding.ivMood5 -> img = R.drawable.ic_mood_5
        }

        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addSelectedMood(img!!)
    }
}