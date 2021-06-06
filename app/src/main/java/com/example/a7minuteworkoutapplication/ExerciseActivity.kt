package com.example.a7minuteworkoutapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeechService
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkoutapplication.databinding.ActivityExerciseBinding
import com.example.a7minuteworkoutapplication.databinding.ConfirmationDialogBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

//extending with TextToSpeech OnInitListener
class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    //counts up whereas timer counts down
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    //Variable for TextToSpeech
    private var tts: TextToSpeech? = null
    //variable for MediaPlayer
    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null

    //resting time from intent
    private var restTime: Int? = null
    //single exercise time from intent
    private var workoutTime: Int? = null

    //resting time for timer
    private var millisInFutureRest: Long? =  null
    //exercise time for timer
    private var millisInFutureWorkout: Long? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarExerciseActivity)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        restTime = intent.extras?.getInt("RestTime")
        millisInFutureRest = (restTime!!*1000).toLong()

        workoutTime = intent.extras?.getInt("WorkoutTime")
        millisInFutureWorkout = (workoutTime!!*1000).toLong()

        //initializing the text to speech
        tts = TextToSpeech(this, this)

        binding.toolbarExerciseActivity.setNavigationOnClickListener {
            makeConfirmationDialog()
        }

        exerciseList = Constants.defaultExerciseList()
        setUpRestView()

        setUpRecyclerView()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null){
            player!!.stop()
        }
    }

    private fun setRestProgressBar(){

        binding.progressBar.progress = restProgress
        binding.tvTimer.text = restTime!!.toString()
        binding.progressBar.max = restTime!!
        restTimer = object: CountDownTimer(millisInFutureRest!!, 1000){
            //is executed on every tick
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = restTime!! -restProgress
                binding.tvTimer.text = (restTime!!-restProgress).toString()
            }
            //is executed when finished
            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                //it notifies data has changed and displays adapter again
                exerciseAdapter!!.notifyDataSetChanged()

                setUpExerciseView()
            }
        }.start()
    }

    private fun setUpRestView(){

        try {
            player = MediaPlayer.create(application, R.raw.press_start)
            //playing it once
            player!!.isLooping = false
            player!!.start()
        } catch (e: Exception){
            e.printStackTrace()
        }

        binding.llRestView.visibility = View.VISIBLE
        binding.llExerciseView.visibility = View.INVISIBLE
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        binding.tvNextExerciseName.text = exerciseList!![currentExercisePosition+1].getName()
        setRestProgressBar()
    }

    private fun setExerciseProgressBar(){
        binding.progressBarExercise.progress = exerciseProgress
        binding.progressBarExercise.max = workoutTime!!
        exerciseTimer = object: CountDownTimer(millisInFutureWorkout!!, 1000){
            //is executed on every tick
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = workoutTime!!-exerciseProgress
                binding.tvTimerExercise.text = (workoutTime!!-exerciseProgress).toString()
            }
            //is executed when finished
            override fun onFinish() {
                //if(currentExercisePosition < 1){
                if (currentExercisePosition < exerciseList!!.size-1){
                    if (exerciseList!![currentExercisePosition].getIsSelected() == true) {
                        exerciseList!![currentExercisePosition].setIsSelected(false)
                        exerciseList!![currentExercisePosition].setIsCompleted(true)
                        exerciseAdapter!!.notifyDataSetChanged()
                    }
                    setUpRestView()
                }else{
                    //finish old activity cuz pressing back should take to home when finished
                    finish()
                    val intent = Intent(this@ExerciseActivity, MoodActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setUpExerciseView(){

        binding.llRestView.visibility = View.INVISIBLE
        binding.llExerciseView.visibility = View.VISIBLE

        if (exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
        binding.ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
        if (binding.tvExerciseName.text.isNotEmpty()){
            speakOut(binding.tvExerciseName.text.toString())
        }
    }

    /**This is TextToSpeech override function
     * Called to signal the completion of the TextToSpeech engine initialization
     * */
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            //setting language
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "The Language specified is not supported")
            }
        } else{
            Log.e("TTS", "Initialization Failed!")
        }
    }

    //function used to speak the text we pass
    private fun speakOut(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null, "")
    }

    private fun setUpRecyclerView(){
        binding.rvExerciseStatus.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)
        binding.rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun makeConfirmationDialog(){
        val confirmationDialog = Dialog(this)
        val binding2 = ConfirmationDialogBinding.inflate(layoutInflater)
        confirmationDialog.setContentView(binding2.root)

        binding2.apply {
            buttonYes.setOnClickListener {
                onBackPressed()
                confirmationDialog.dismiss()
            }

            buttonNo.setOnClickListener {
                confirmationDialog.dismiss()
            }
        }
        confirmationDialog.show()
    }
}