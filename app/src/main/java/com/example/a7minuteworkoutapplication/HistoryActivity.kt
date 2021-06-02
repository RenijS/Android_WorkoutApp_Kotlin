package com.example.a7minuteworkoutapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkoutapplication.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHistoryActivity)

        val actionBar = supportActionBar
        if (actionBar != null){
            //setting back button
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "History"
        }

        binding.toolbarHistoryActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        fillRecyclerView()
    }

    private fun fillRecyclerView(){
        val dbHandler = SqliteOpenHelper(this, null)
        val allDataList = dbHandler.getAllDataList()
        Log.e("HistoryActivity", "" + allDataList)

        if (allDataList.size > 0){
            binding.tvNoData.visibility = View.GONE
            binding.llRvMain.visibility = View.VISIBLE

            binding.rvHistory.layoutManager = LinearLayoutManager(this)
            binding.rvHistory.adapter = HistoryAdapter(this, allDataList)
        } else{
            binding.llRvMain.visibility = View.GONE
            binding.tvNoData.visibility = View.VISIBLE
        }
    }
}