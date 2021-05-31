package com.example.a7minuteworkoutapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkoutapplication.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding

    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBmiActivity)

        val actionBar = supportActionBar
        if (actionBar != null){
            //setting back button
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Calculate BMI"
        }

        binding.toolbarBmiActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.buttonCalculate.setOnClickListener {
            if (currentVisibleView == METRIC_UNITS_VIEW && validateMetricUnit()){
                //dividing by 100 to change to meter
                val height = binding.etMetricUnitHeight.text.toString().toFloat()/100
                val weight = binding.etMetricUnitWeight.text.toString().toFloat()

                //bmi formula
                val bmi = weight/(height*height)

                displayBmiResult(bmi)
            } else if (currentVisibleView == US_UNITS_VIEW && validateUsUnit()){
            //dividing by 100 to change to meter
                val Inch = binding.etUsUnitInch.text.toString().toFloat()
                val Feet = binding.etUsUnitFt.text.toString().toFloat()
                val weight = binding.etUsUnitWeight.text.toString().toFloat()

                val height = Feet + Inch*12
                //bmi formula
                val bmi = 703*(weight/(height*height))

                displayBmiResult(bmi)
        } else{
            Toast.makeText(this, "Invalid inputs", Toast.LENGTH_SHORT).show()
        }
        }

        binding.rgUnits.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.rbMetricUnits){
                changeVisibilityForUnitsView()
            } else{
                changeVisibilityForUnitsView()
            }
        }
    }

    private fun displayBmiResult(bmi:Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi <= 15f){
            bmiLabel = "Very Severely UnderWeight"
            bmiDescription = "Oops! You really need to take better care of Yourself! Eat more"
        } else if (bmi > 15f && bmi <= 16f){
            bmiLabel = "Severely Underweight"
            bmiDescription = "Oops! You need to take better care of Yourself! Eat more"
        } else if(bmi > 16f && bmi <= 18.5f){
            bmiLabel = "Underweight"
            bmiDescription = "You need to take better care of Yourself! Eat more"
        } else if(bmi > 18.5f && bmi <= 25f){
            bmiLabel = "Normal"
            bmiDescription = "Congratulation! You are in a good shape"
        } else if (bmi > 25f && bmi <= 30f){
            bmiLabel = "Overweight"
            bmiDescription = "You need to take care of yourself! Workout more"
        } else if (bmi > 30f && bmi <= 35f){
            bmiLabel = "Obese Class (Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of Yourself! Workout more"
        } else if (bmi > 35f && bmi <= 40f ){
            bmiLabel = "Obese Class (Severely obese)"
            bmiDescription = "OMG! You are in dangerous situation! Act now"
        } else {
            bmiLabel = "Obese Class (Very Severely obese)"
            bmiDescription = "OMG! You are in very dangerous situation! Act now!"
        }
        binding.llBmi.visibility = View.VISIBLE
        binding.tvBmiValue.text = String.format("%.2f", bmi.toDouble())
        binding.tvBmiType.text = bmiLabel
        binding.tvBmiDesc.text = bmiDescription
    }

    //checking if its null or ot
    private fun validateMetricUnit(): Boolean{
        var isValid = true

        if (binding.etMetricUnitHeight.text.isNullOrEmpty() || binding.etMetricUnitWeight.text.isNullOrEmpty()){
            isValid = false
        }
        return isValid
    }
    //checking if its null or not
    private fun validateUsUnit(): Boolean{
        var isValid = true

        if (binding.etUsUnitWeight.text.isNullOrEmpty() || binding.etUsUnitFt.text.isNullOrEmpty() || binding.etUsUnitInch.text.isNullOrEmpty()){
            isValid = false
        }
        return isValid
    }

    //changing visibility when units is changed
    private fun changeVisibilityForUnitsView(){
        if (currentVisibleView.equals(METRIC_UNITS_VIEW)){
            binding.llMetricEt.visibility = View.INVISIBLE
            binding.llUsEt.visibility = View.VISIBLE
            currentVisibleView = US_UNITS_VIEW
            binding.etMetricUnitHeight.text!!.clear()
            binding.etMetricUnitWeight.text!!.clear()
        } else{
            binding.llUsEt.visibility = View.INVISIBLE
            binding.llMetricEt.visibility = View.VISIBLE
            currentVisibleView = METRIC_UNITS_VIEW
            binding.etUsUnitWeight.text!!.clear()
            binding.etUsUnitFt.text!!.clear()
            binding.etUsUnitInch.text!!.clear()
        }
        /**   bmi result also needs to be invisible
        *GONE makes it gone so no space is taken
        *INVISIBLE just makes it invisible so it's still there taking space
         **/
        binding.llBmi.visibility = View.GONE
    }
}