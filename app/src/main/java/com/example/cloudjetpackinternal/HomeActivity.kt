package com.example.cloudjetpackinternal

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var etTitle = findViewById<EditText>(R.id.et_title)
        var etNote = findViewById<EditText>(R.id.et_note)
        var btAdd = findViewById<Button>(R.id.btn_add)
        var btShow = findViewById<Button>(R.id.bt_showList)

        auth = Firebase.auth
        var currentUser = auth.currentUser?.uid.toString()
        val db = Firebase.firestore

        btAdd.setOnClickListener {

            val sTitle = etTitle.text.toString()
            val sNote = etNote.text.toString()

            val currentDateTime = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val dateAsString = currentDateTime.format(dateFormatter)
            val timeAsString = currentDateTime.format(timeFormatter)

            val note = hashMapOf(
                "title" to sTitle,
                "note" to sNote,
                "date" to dateAsString,
                "time" to timeAsString,
            )

            db.collection("UserData").document(currentUser.toString()).collection("Notes").document()
                .set(note)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }

        btShow.setOnClickListener {
            var i = Intent(applicationContext,DisplayActivity::class.java)
            startActivity(i)
        }
    }
}