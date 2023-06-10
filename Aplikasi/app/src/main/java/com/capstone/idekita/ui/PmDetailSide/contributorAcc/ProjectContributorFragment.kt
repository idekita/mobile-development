package com.capstone.idekita.ui.PmDetailSide.contributorAcc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.capstone.idekita.R
import com.capstone.idekita.databinding.FragmentMyProjectBinding
import com.capstone.idekita.databinding.FragmentProjectContributorBinding
import com.capstone.idekita.ui.myProject.MyProjectFragment
import com.capstone.idekita.ui.myProject.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ProjectContributorFragment : Fragment() {
    private var _binding: FragmentProjectContributorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectContributorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }




}