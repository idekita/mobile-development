package com.capstone.idekita.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.adapter.ListAllProjectAdapter
import com.capstone.idekita.databinding.FragmentHomeBinding
import com.capstone.idekita.dummy.adapter.RecentProjectAdapter
import com.capstone.idekita.dummy.data.DummyList
import com.capstone.idekita.dummy.data.DummyListHorizotal
import com.capstone.idekita.dummy.data.Response
import com.capstone.idekita.response.ProjectsItem
import com.capstone.idekita.ui.detailProject.DetailProjectActivity
import com.capstone.idekita.ui.listKategori.ListKategoriActivity
import com.capstone.idekita.ui.login.LoginActivity


class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel> {
        MainViewModelFactory.getInstance(requireContext())
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //setAction()
        setViewModel()
        //showdata()


        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root


    }


    private fun setViewModel() {
        homeViewModel.getUser().observe(viewLifecycleOwner) { user ->
            if (user.isLogin) {
                homeViewModel.listProject.observe(viewLifecycleOwner) {
                    ShowRecycleList(it)
                }
                homeViewModel.getAllProject("Bearer ${user.token}")


                binding.btnKeKategori.setOnClickListener() {
                    val intent = Intent(requireContext(), ListKategoriActivity::class.java)
                    startActivity(intent)
                }

            } else {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)

            }
        }
    }


    private fun ShowRecycleList(listProject: List<ProjectsItem>) {

        //Recycle View Rekomendasi
        binding.rvRekomendasi.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val projectListAdapter = ListAllProjectAdapter(listProject)
        binding.rvRekomendasi.adapter = projectListAdapter

        projectListAdapter.setOnItemClickCallback(object :
            ListAllProjectAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ProjectsItem) {
                val intent = Intent(requireContext(), DetailProjectActivity::class.java)

                val bundle = Bundle()
                bundle.putString("extra_name", data.nmProyek)
                bundle.putString("extra_desc", data.deskripsi)
                bundle.putString("extra_photo", data.gambar)
                bundle.putString("extra_creator", data.creator)
                bundle.putString("extra_status", data.status)
                bundle.putString("extra_start", data.tanggalMulai)
                bundle.putString("extra_end", data.tanggalSelesai)
                bundle.putString("extra_category", data.category.nmKategori)

                intent.putExtras(bundle)

                startActivity(intent)
            }
        })


        //Recycle View Latest
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecent.layoutManager = layoutManager
        val projectListAdapter2 = ListAllProjectAdapter(listProject)
        binding.rvRecent.adapter = projectListAdapter2

        projectListAdapter2.setOnItemClickCallback(object :
            ListAllProjectAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ProjectsItem) {
                val intent = Intent(requireContext(), DetailProjectActivity::class.java)

                val bundle = Bundle()
                bundle.putString("extra_name", data.nmProyek)
                bundle.putString("extra_desc", data.deskripsi)
                bundle.putString("extra_photo", data.gambar)
                bundle.putString("extra_creator", data.creator)
                bundle.putString("extra_status", data.status)
                bundle.putString("extra_start", data.tanggalMulai)
                bundle.putString("extra_end", data.tanggalSelesai)
                bundle.putString("extra_category", data.category.nmKategori)

                intent.putExtras(bundle)

                startActivity(intent)
            }
        })


    }

    private fun showdata() {


        //dummy project rekomen
        val layoutManager2 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRekomendasi.layoutManager = layoutManager2
        val adapter2 = RecentProjectAdapter(DummyListHorizotal.getTheList() as ArrayList<Response>)
        binding.rvRekomendasi.adapter = adapter2

        adapter2.setOnItemClickCallback(object : RecentProjectAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Response) {
                val intent = Intent(requireContext(), DetailProjectActivity::class.java)

                val bundle = Bundle()
                bundle.putString("extra_name", data.name)
                bundle.putString("extra_desc", data.desc)
                bundle.putInt("extra_photo", data.photo)

                intent.putExtras(bundle)

                startActivity(intent)

            }

        })


        //  dummy rv projek terbaru
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvRecent.layoutManager = layoutManager
        val adapter = RecentProjectAdapter(DummyList.getTheList() as ArrayList<Response>)

        binding.rvRecent.adapter = adapter

    }


}