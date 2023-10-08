package com.example.giroscopio

import android.content.Context
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var pelotita: pelotita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pelotita = pelotita(this, null)
        setContentView(pelotita)
    }

    override fun onResume() {
        super.onResume()
        pelotita.start()
    }

    override fun onPause() {
        super.onPause()
        pelotita.stop()
    }
}