package com.capstone.idekita.ui.myProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.idekita.databinding.FragmentFinishBinding
import com.capstone.idekita.dummy.adapter.MyProjectAdapter
import com.capstone.idekita.dummy.data.MyProject
import com.capstone.idekita.dummy.data.Ongoing


class FinishFragment : Fragment() {

    private var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // dummy projek Finish

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvOngoing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvOngoing.addItemDecoration(itemDecoration)
        val adapter = MyProjectAdapter(Ongoing.getTheDone() as ArrayList<MyProject>)

        binding.rvOngoing.adapter = adapter

    }

}