package com.example.pedometerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    private lateinit var edtUserpass: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnStart: Button
    private lateinit var btnRegister: Button
    private var mAuth: FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        edtUserpass=findViewById(R.id.Edt_Password)
        edtEmail=findViewById(R.id.Edt_Email)
        btnStart=findViewById(R.id.btn_Start)
        btnRegister=findViewById(R.id.btn_reg)
        mAuth= FirebaseAuth.getInstance()
        btnRegister.setOnClickListener {
            val intent= Intent(this,RegisterPage::class.java)
            startActivity(intent)
        }
        btnStart.setOnClickListener {
            //  Toast.makeText(this,"logging in",Toast.LENGTH_SHORT).show()
            var useremail = edtEmail.text.toString().trim()
            var userpass =edtUserpass.text.toString().trim()



            mAuth!!.signInWithEmailAndPassword(useremail,userpass).addOnCompleteListener {
                    task : Task<AuthResult> ->

                if (task.isSuccessful){
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
                    Log.d("Error-->",task.exception.toString())
                }
            }
        }
    }
}