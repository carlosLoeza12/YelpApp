package com.example.yeplapp.ui.bussinesdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.yeplapp.R
import com.example.yeplapp.databinding.FragmentBussinesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessDetailsFragment : Fragment(R.layout.fragment_bussines_details) {

    private lateinit var binding: FragmentBussinesDetailsBinding
    private val args by navArgs<BusinessDetailsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBussinesDetailsBinding.bind(view)
    }
}