package com.capstone.idekita.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.capstone.idekita.R
import com.capstone.idekita.databinding.FragmentHomeBinding
import com.capstone.idekita.databinding.FragmentMyProjectBinding
import com.capstone.idekita.dummy.adapter.RecentProjectAdapter
import com.capstone.idekita.dummy.data.DummyList
import com.capstone.idekita.dummy.data.DummyListHorizotal
import com.capstone.idekita.dummy.data.Response
import com.capstone.idekita.ui.detailProject.DetailProjectActivity
import com.capstone.idekita.ui.myProject.MyProjectFragment
import com.capstone.idekita.ui.myProject.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // dummy projek rekomendasi


            val layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            binding.rvRekomendasi.layoutManager = layoutManager2
            val itemDecoration2 = DividerItemDecoration(requireContext(), layoutManager2.orientation)
            //binding.rvRekomendasi.addItemDecoration(itemDecoration2)

            val adapter2 = RecentProjectAdapter(DummyListHorizotal.getTheList() as ArrayList<Response>)

            binding.rvRekomendasi.adapter = adapter2

            adapter2.setOnItemClickCallback(object : RecentProjectAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Response) {
                    val intent = Intent(requireContext(),DetailProjectActivity::class.java)

                    val bundle = Bundle()
                    bundle.putString("extra_name",data.name)
                    bundle.putString("extra_desc",data.desc)
                    bundle.putInt("extra_photo",data.photo)

                    intent.putExtras(bundle)

                    startActivity(intent)

                }

            })








        //   dummy rv projek terbaru
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvRecent.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        //binding.rvRecent.addItemDecoration(itemDecoration)

        val adapter = RecentProjectAdapter(DummyList.getTheList() as ArrayList<Response>)

        binding.rvRecent.adapter = adapter


    }


}