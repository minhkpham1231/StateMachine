package com.example.statemachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.statemachine.viewmodel.StateViewModel

class MainActivity : AppCompatActivity() {
    private val countryViewModel by lazy {
        ViewModelProvider(this)[StateViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        countryViewModel.states.observe(this) {
            // Displaying the text here from the livedata
            findViewById<TextView>(R.id.text).text = it
        }
        countryViewModel.getState()
    }
}