package com.capstone.idekita.ui.PmDetailSide.contributorAcc

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.idekita.ui.myProject.FinishFragment
import com.capstone.idekita.ui.myProject.OngoingFragment


class  ContributorSectionPager (activity: AppCompatActivity) : FragmentStateAdapter(activity) {


    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ProjectContributorFragment()
            1 -> fragment = WaitingContributorFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}