package com.example.cloudjetpackinternal

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudjetpackinternal.Adapter.NotesAdapter
import com.example.cloudjetpackinternal.Models.NoteItemModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class DisplayActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val arrayList = ArrayList<NoteItemModel>()

        var recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        auth = Firebase.auth
        var currentUser = auth.currentUser?.uid.toString()

        val db = Firebase.firestore

        db.collection("UserData").document(currentUser.toString()).collection("Notes")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    arrayList.add(document.toObject(NoteItemModel::class.java))
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val adapter = NotesAdapter(arrayList)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }



    }
}