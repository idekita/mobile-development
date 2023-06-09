package com.capstone.idekita.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.idekita.MainViewModelFactory
import com.capstone.idekita.R
import com.capstone.idekita.adapter.LoadingStateAdapter
import com.capstone.idekita.adapter.ProjectPagingAdapter
import com.capstone.idekita.databinding.FragmentSearchBinding
import com.capstone.idekita.ui.home.HomeViewModel



class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel by viewModels<HomeViewModel> {
        MainViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)



        val searchItem = binding.searchView

        searchItem.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    binding.rvSearchProjecet.scrollToPosition(0)
                    searchViewModel.getUser().observe(viewLifecycleOwner){user ->
                        getData(user.token,query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        return binding.root


    }

    private fun getData(token:String,query:String){
        val adapter = ProjectPagingAdapter()
        binding.rvSearchProjecet.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchProjecet.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )

        searchViewModel.getAllProjectPaging(token).observe(this) {
            adapter.submitData(lifecycle, it)
        }

    }








}