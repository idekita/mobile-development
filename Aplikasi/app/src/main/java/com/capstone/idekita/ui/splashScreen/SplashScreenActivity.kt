package com.capstone.idekita.ui.splashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.idekita.R
import com.capstone.idekita.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}