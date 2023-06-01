package com.capstone.idekita.ui.addProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.idekita.R
import com.capstone.idekita.databinding.FragmentAddProjectBinding


class AddProjectFragment : Fragment() {

    private var _binding:FragmentAddProjectBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_project, container, false)



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}