package com.example.yeplapp.ui.bussinesdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yeplapp.R
import com.example.yeplapp.databinding.FragmentBussinesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessDetailsFragment : Fragment(R.layout.fragment_bussines_details) {

    private lateinit var binding: FragmentBussinesDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBussinesDetailsBinding.bind(view)
    }
}