package com.capstone.idekita.ui.listKategori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.idekita.MainViewModel
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.R
import com.capstone.idekita.adapter.LoadingStateAdapter
import com.capstone.idekita.adapter.ProjectPagingAdapter
import com.capstone.idekita.databinding.ActivityListKategoriBinding
import com.capstone.idekita.ui.home.HomeViewModel

class ListKategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListKategoriBinding
    private val mainViewModel by viewModels<HomeViewModel> {
        MainViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpView()

    }

    private fun setUpView(){
        mainViewModel.getUser().observe(this){user->
            getData(user.token)

        }
    }

    private fun getData(token:String){
        val adapter = ProjectPagingAdapter()
        binding.rvListProjectCateogry.layoutManager = LinearLayoutManager(this)
        binding.rvListProjectCateogry.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )

        mainViewModel.getAllProjectPaging(token).observe(this,{
            adapter.submitData(lifecycle,it)
        })

    }


}