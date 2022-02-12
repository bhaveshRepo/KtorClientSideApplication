package com.example.ktorclientapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ktorclientapplication.api.PostServiceInterface

class MainActivity : AppCompatActivity() {

    private val service = PostServiceInterface.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}