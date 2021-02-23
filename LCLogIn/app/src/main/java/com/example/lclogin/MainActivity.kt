package com.example.lclogin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","Create)))")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity","Start)))")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity","Resume)))")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity","Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity","Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity","Destroy")
    }
}