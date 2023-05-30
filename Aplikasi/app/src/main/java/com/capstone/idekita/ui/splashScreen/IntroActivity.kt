package com.capstone.idekita.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.idekita.databinding.ActivityIntroBinding
import com.capstone.idekita.ui.login.LoginActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btToLogin.setOnClickListener{
            startActivity(
                Intent(this,LoginActivity::class.java)
            )
            finish()
        }
    }
}