package com.example.writeeverything

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.Intent as Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(supportActionBar != null) supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        },2000)
    }
}