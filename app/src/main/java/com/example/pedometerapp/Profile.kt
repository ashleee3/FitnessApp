package com.example.pedometerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Profile : AppCompatActivity() {
    private lateinit var edtUsername:EditText
    private lateinit var edtAge:EditText
    private lateinit var edtSteps:EditText

    private lateinit var btnSubmit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        edtUsername = findViewById(R.id.edt_Username)
        edtAge = findViewById(R.id.edt_Age)
        edtSteps = findViewById(R.id.Steps)
        btnSubmit = findViewById(R.id.Btn_Submit)


        btnSubmit.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
    }
}