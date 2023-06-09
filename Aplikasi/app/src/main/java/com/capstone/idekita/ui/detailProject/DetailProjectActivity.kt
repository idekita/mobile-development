package com.capstone.idekita.ui.detailProject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.adapter.ContributorProjectAdapter
import com.capstone.idekita.databinding.ActivityDetailProjectBinding
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.ui.home.HomeViewModel
import java.net.IDN

//import com.capstone.idekita.model.wisataEntity

class DetailProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProjectBinding

    private val homeViewModel by viewModels<HomeViewModel> {
        MainViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val dataPhoto = extras.getString("extra_photo")
            val idProyek = extras.getInt("extra_id")

            homeViewModel.getUser().observe(this) { user ->
                if (extras != null) {
                    homeViewModel.listContributor.observe(this){
                        showContributor(it)
                    }
                    homeViewModel.getAllContributor("Bearer ${user.token}",idProyek)
                }
            }

            binding.tvId.text = extras.getInt("extra_id").toString()
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


        homeViewModel.getUser().observe(this) { user ->
            if (extras != null) {
                if (user.name == extras.getString("extra_creator")) {
                    binding.btnDaftarContributor.visibility = View.VISIBLE
                    binding.btnJoin.visibility = View.GONE
                } else {
                    binding.btnDaftarContributor.visibility = View.GONE
                }
            }
        }

    }

    private fun showContributor(listContributor : List<ContributorsItem>) {
        binding.rvListContributor.layoutManager = LinearLayoutManager(this)
        val userListAdapter =ContributorProjectAdapter(listContributor)
        binding.rvListContributor.adapter = userListAdapter


    }


}