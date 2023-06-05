package com.capstone.idekita.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.idekita.R
import com.capstone.idekita.databinding.FragmentHomeBinding
import com.capstone.idekita.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding.nameTV.text = getString()

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


}