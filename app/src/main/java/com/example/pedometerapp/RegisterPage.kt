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

class RegisterPage : AppCompatActivity() {
    private lateinit var edt_name: EditText
    private lateinit var edt_address: EditText
    private lateinit var edt_pass: EditText
    private lateinit var edt_conpass: EditText
    private lateinit var btnRegg: Button
    private var mAuth: FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        edt_name=findViewById(R.id.edt_Name)
        edt_address=findViewById(R.id.edt_Mail)
        edt_pass=findViewById(R.id.Password)
        edt_conpass=findViewById(R.id.Con_Password)
        btnRegg=findViewById(R.id.Btn_Register)
        mAuth= FirebaseAuth.getInstance()
        btnRegg.setOnClickListener {
            Toast.makeText(this,"Signing Up..", Toast.LENGTH_SHORT).show()

            //  Log.d(this.toString(),"Error-->")
            var useremail = edt_address.text.toString().trim()
            var userpass =edt_pass.text.toString().trim()



            mAuth!!.createUserWithEmailAndPassword(useremail,userpass).addOnCompleteListener {
                    task : Task<AuthResult> ->

                if (task.isSuccessful){
                    Toast.makeText(this,"Account created Successfully", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"register Failed", Toast.LENGTH_SHORT).show()
                    Log.d("Error-->",task.exception.toString())
                }
            }
        }
    }
}