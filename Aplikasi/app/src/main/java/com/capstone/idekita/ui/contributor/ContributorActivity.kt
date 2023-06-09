package com.capstone.idekita.ui.contributor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.idekita.databinding.ActivityContributorBinding

class ContributorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContributorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContributorBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}