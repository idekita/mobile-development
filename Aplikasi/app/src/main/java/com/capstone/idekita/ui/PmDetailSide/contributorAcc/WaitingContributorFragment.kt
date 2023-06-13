package com.capstone.idekita.ui.PmDetailSide.contributorAcc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.R
import com.capstone.idekita.adapter.ContributorProjectAdapter
import com.capstone.idekita.adapter.WaitingContributorProjectAdapter
import com.capstone.idekita.databinding.FragmentProjectContributorBinding
import com.capstone.idekita.databinding.FragmentWaitingContributorBinding
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.ui.home.HomeViewModel


class WaitingContributorFragment : Fragment() {


    private var _binding: FragmentWaitingContributorBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel> {
        MainViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWaitingContributorBinding.inflate(inflater, container, false)

        val extras = requireActivity().intent.extras
        if(extras != null){
            val idProyek = extras.getInt("extra_id")

            //binding.parsingFromDetail.text = idProyek.toString()
            homeViewModel.getUser().observe(viewLifecycleOwner){user->
                homeViewModel.listContributorWait.observe(viewLifecycleOwner){
                    showContributor(it)
                }
                homeViewModel.getAllContributorWait(user.token,idProyek)

            }

        }

        return binding.root
    }


    private fun showContributor(listContributor : List<ContributorsItem>) {
        binding.rvWaitingContributor.layoutManager = LinearLayoutManager(requireContext())
        val userListAdapter = WaitingContributorProjectAdapter(listContributor)
        binding.rvWaitingContributor.adapter = userListAdapter


    }




}