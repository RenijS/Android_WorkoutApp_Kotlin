package com.example.a7minuteworkoutapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.a7minuteworkoutapplication.databinding.ActivityFinalScreenBinding
import com.example.a7minuteworkoutapplication.databinding.ConfirmationDialogBinding

class FinalScreenActivity : AppCompatActivity(){

    private lateinit var binding:ActivityFinalScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarExerciseActivity)
        //actionbar
        val actionBar = supportActionBar
        if (actionBar != null){
            //set back button
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarExerciseActivity.setNavigationOnClickListener {
            onBackPressed()

        }

        binding.buttonFinish.setOnClickListener {
            finish()
        }
    }
}