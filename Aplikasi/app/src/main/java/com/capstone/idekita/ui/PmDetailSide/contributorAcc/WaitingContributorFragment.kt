package com.capstone.idekita.ui.PmDetailSide.contributorAcc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.R
import com.capstone.idekita.adapter.ContributorProjectAdapter
import com.capstone.idekita.adapter.WaitingContributorProjectAdapter
import com.capstone.idekita.databinding.FragmentProjectContributorBinding
import com.capstone.idekita.databinding.FragmentWaitingContributorBinding
import com.capstone.idekita.response.Contributor
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.response.ContributorsItemWait
import com.capstone.idekita.result.TheResult
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
            //val idContributor = extras.getInt("")

            homeViewModel.getUser().observe(viewLifecycleOwner){user->
                homeViewModel.listContributorWait.observe(viewLifecycleOwner){
                    showContributor(it,user.token,"Anggota")
                }
                homeViewModel.getAllContributorWait(user.token,idProyek)
            }

        }

        return binding.root
    }


    private fun accProject(token:String,id:Int,statLamar:String,role:String){
        homeViewModel.AccProject(token, id, statLamar, role).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is TheResult.Loading -> {

                    }
                    is TheResult.Success -> {
                        Toast.makeText(requireContext(), result.data.message, Toast.LENGTH_SHORT).show()
                        Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is TheResult.Error -> {
                        Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showContributor(listContributor : List<ContributorsItemWait>, token: String,role: String) {
        binding.rvWaitingContributor.layoutManager = LinearLayoutManager(requireContext())
        val userWaiting = WaitingContributorProjectAdapter(listContributor,token,role)
        binding.rvWaitingContributor.adapter = userWaiting

        userWaiting.setOnItemClickCallback(object :WaitingContributorProjectAdapter.OnItemClickCallback{

            override fun onItemClicked(data: ContributorsItemWait) {
                val idContributor = data.id
                accProject(token,idContributor,"diterima",role)
                Toast.makeText(requireContext(),"Ini Tombol Terima",Toast.LENGTH_SHORT).show()

            }

            override fun onItemTolakClicked(data: ContributorsItemWait) {
                Toast.makeText(requireContext(),"Ini Tombol TOLAKKK",Toast.LENGTH_SHORT).show()
                val idContributor = data.id
                accProject(token,idContributor,"ditolak",role)
            }

        })

    }




}