package com.capstone.idekita.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.idekita.databinding.ActivityRegisterBinding



class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

    }
}