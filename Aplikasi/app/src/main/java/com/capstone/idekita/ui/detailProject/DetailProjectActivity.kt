package com.capstone.idekita.ui.detailProject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.capstone.idekita.databinding.ActivityDetailProjectBinding
//import com.capstone.idekita.model.wisataEntity

class DetailProjectActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailProjectBinding

//    companion object {
//        const val EXTRA_DATA = "extra_data"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            val dataPhoto = extras.getString("extra_photo")
            binding.nameTV.text = extras.getString("extra_name")
            binding.descTV.text = extras.getString("extra_desc")
            binding.creatorTV.text = extras.getString("extra_creator")
            binding.statusTV.text = extras.getString("extra_status")
            binding.mulaiTV.text = extras.getString("extra_start")
            binding.endTV.text = extras.getString("extra_end")
            binding.kategoriTV.text = extras.getString("extra_category")
            Glide.with(this)
                .load(dataPhoto)
                .into(binding.ivDetail)
        }

    }


}