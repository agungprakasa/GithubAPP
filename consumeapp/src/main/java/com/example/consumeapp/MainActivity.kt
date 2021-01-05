package com.example.consumeapp

import android.database.ContentObserver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumeapp.Constants.Companion.CONTENT_URI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter:ConsumeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "ConsumeApp"

        val uriWithId = Uri.parse(CONTENT_URI.toString())
        val cursor = contentResolver?.query(uriWithId, null, null, null, null)

        if (cursor != null) {
          cursor.moveToNext()
            Log.d(""," TEST ${cursor.count}")
        }

        setupRecyclerView()

    }

    private fun setupRecyclerView(){
        myAdapter = ConsumeAdapter()
        rvFavorit.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(application)
        }
    }
}

