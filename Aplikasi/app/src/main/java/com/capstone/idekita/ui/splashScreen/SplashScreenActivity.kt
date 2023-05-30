package com.capstone.idekita.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.capstone.idekita.databinding.ActivitySplashScreenBinding
import com.capstone.idekita.ui.login.LoginActivity


@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Handler().postDelayed({
            startActivity(
                Intent(this@SplashScreenActivity, IntroActivity::class.java)
            )
            finish()
        },DELAY)
    }


    companion object{
        const val DELAY : Long = 2000
    }
}