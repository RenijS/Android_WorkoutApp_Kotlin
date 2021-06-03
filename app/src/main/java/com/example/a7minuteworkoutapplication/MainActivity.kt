package com.example.a7minuteworkoutapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a7minuteworkoutapplication.databinding.ActivityMainBinding
import com.example.a7minuteworkoutapplication.databinding.ChangeTimeDialogBinding
import com.example.a7minuteworkoutapplication.databinding.ConfirmationDialogBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var restTime: Int = 10
    private var exerciseTime: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.llStart.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("RestTime", restTime)
            intent.putExtra("WorkoutTime", exerciseTime)
            startActivity(intent)
        }

        binding.llBmi.setOnClickListener {
            val intent = Intent(this, BmiActivity::class.java)
            startActivity(intent)
        }

        binding.llHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.buttonChangeRestTime.setOnClickListener {
            makeChangeRestTimeDialog()
        }

        binding.buttonChangeWorkoutTime.setOnClickListener {
            makeChangeWorkoutTimeDialog()
        }
    }

    private fun makeChangeRestTimeDialog(){
        val restTimeDialog = Dialog(this)
        val binding2 = ChangeTimeDialogBinding.inflate(layoutInflater)
        restTimeDialog.setContentView(binding2.root)

        binding2.apply {
            buttonEnter.setOnClickListener {
                if (etSec.text.isNotEmpty()){
                    restTime = etSec.text.toString().toInt()
                    restTimeDialog.dismiss()
                } else{
                    Toast.makeText(this@MainActivity, "Input invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
        restTimeDialog.show()
    }

    private fun makeChangeWorkoutTimeDialog(){
        val restTimeDialog = Dialog(this)
        val binding2 = ChangeTimeDialogBinding.inflate(layoutInflater)
        restTimeDialog.setContentView(binding2.root)

        binding2.apply {
            buttonEnter.setOnClickListener {
                if (etSec.text.isNotEmpty()){
                    exerciseTime = etSec.text.toString().toInt()
                    restTimeDialog.dismiss()
                } else{
                    Toast.makeText(this@MainActivity, "Input invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
        restTimeDialog.show()
    }
}