package com.capstone.idekita.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.idekita.MainActivity
import com.capstone.idekita.R
import com.capstone.idekita.databinding.ActivityLoginBinding
import com.capstone.idekita.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.LinkKedaftar.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
        }

    }
}