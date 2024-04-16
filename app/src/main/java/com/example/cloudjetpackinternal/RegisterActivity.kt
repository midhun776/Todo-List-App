package com.example.cloudjetpackinternal

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var etName = findViewById<EditText>(R.id.et_name)
        var etEmail = findViewById<EditText>(R.id.et_email)
        var etPass = findViewById<EditText>(R.id.et_password)
        var btnRegister = findViewById<Button>(R.id.btn_register)
        var tvLogin = findViewById<TextView>(R.id.login)

        auth = Firebase.auth

        btnRegister.setOnClickListener {

            val sName = etName.text.toString()
            val sEmail = etEmail.text.toString()
            val sPass = etPass.text.toString()

            auth.createUserWithEmailAndPassword(sEmail, sPass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(
                            applicationContext,
                            "Authentication successful!.",
                            Toast.LENGTH_SHORT,
                        ).show()

                        var i = Intent(applicationContext,MainActivity::class.java)
                        startActivity(i)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            applicationContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        tvLogin.setOnClickListener {
            var i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
        }
    }
}