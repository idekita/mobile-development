package com.capstone.idekita.ui.PmDetailSide.contributorAcc

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.R
import com.capstone.idekita.adapter.ContributorProjectAdapter
import com.capstone.idekita.databinding.FragmentMyProjectBinding
import com.capstone.idekita.databinding.FragmentProjectContributorBinding
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.response.ProjectsItem
import com.capstone.idekita.ui.PmDetailSide.PmDetailProjectActivity
import com.capstone.idekita.ui.home.HomeViewModel
import com.capstone.idekita.ui.myProject.MyProjectFragment
import com.capstone.idekita.ui.myProject.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ProjectContributorFragment : Fragment() {
    private var _binding: FragmentProjectContributorBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel> {
        MainViewModelFactory.getInstance(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectContributorBinding.inflate(inflater, container, false)

        val extras = requireActivity().intent.extras
        if (extras != null){
            val idProyek = extras.getInt("extra_id")

            binding.parsingFromDetail.text = idProyek.toString()

            homeViewModel.getUser().observe(viewLifecycleOwner){user->
                homeViewModel.listContributor.observe(viewLifecycleOwner){
                    showContributor(it)
                }
                homeViewModel.getAllContributor("Bearer ${user.token}",idProyek)
            }


        }


        return binding.root
    }


    private fun showContributor(listContributor : List<ContributorsItem>) {
        binding.rvListContributor.layoutManager = LinearLayoutManager(requireContext())
        val userListAdapter = ContributorProjectAdapter(listContributor)
        binding.rvListContributor.adapter = userListAdapter


    }




}