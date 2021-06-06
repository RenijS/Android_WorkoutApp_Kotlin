package com.example.a7minuteworkoutapplication

import android.app.Dialog
import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.a7minuteworkoutapplication.databinding.ActivityFinalScreenBinding
import com.example.a7minuteworkoutapplication.databinding.ConfirmationDialogBinding
import java.text.SimpleDateFormat
import java.util.*

class FinalScreenActivity : AppCompatActivity(){

    private lateinit var binding:ActivityFinalScreenBinding

    var selectedImg :Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedMood = intent.extras
         selectedImg = selectedMood!!.getInt("Selected")


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

        savingDataToDatabase()
    }

    private fun savingDataToDatabase(){
        val calendar = Calendar.getInstance()
        //current date time
        val dateTime = calendar.time
        Log.e("DATE", "" + dateTime)

        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addData(MyDataClass(date,selectedImg!!))
        Log.e("DATE", "added $date")
    }
}