package com.example.pedometerapp

import android.app.usage.UsageEvents
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(),SensorEventListener {
    private var sensorManager:SensorManager? = null
    private  var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    private lateinit var Steps_Taken: TextView
    private lateinit var progress_bar: CircularProgressBar




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        LoadData()

     Steps_Taken =findViewById(R.id.Steps_Taken)
        progress_bar= findViewById(R.id.progress_bar)
        sensorManager =getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor== null){
            Toast.makeText(this,"NO sensor detected on this device", Toast.LENGTH_SHORT)
        }else{
            sensorManager?.registerListener(this,stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }
    override fun onSensorChanged(event: SensorEvent) {
        if (running){

            totalSteps= event!!.values[0]
            val  currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()
            Steps_Taken.text = ("$currentSteps")

            progress_bar.apply{
                this.setProgressWithAnimation(currentSteps.toFloat())
            }


        }


    }
    fun resetSteps(){
        Steps_Taken.setOnClickListener{
            Toast.makeText(this,"Long Press to reset steps", Toast.LENGTH_SHORT).show()
        }
        Steps_Taken.setOnLongClickListener {
            previousTotalSteps = totalSteps
            Steps_Taken.text = 0.toString()
            saveData()
            true
        }
    }
    private fun saveData(){
        val sharedPreferences  = getSharedPreferences("my prefs",Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? =sharedPreferences.edit()//changed
        editor!!.putFloat("key1",previousTotalSteps)
        editor.apply {  }
    }
    private fun LoadData(){
        val sharedPreferences :SharedPreferences = getSharedPreferences("myPrefs",Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1",0f)


        previousTotalSteps= savedNumber
        Log.d("Main-->","$savedNumber")
    }

    private fun setProgressWithAnimation(toFloat: Float) {

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}