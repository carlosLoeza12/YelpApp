package com.example.yeplapp.ui.searchbusinesses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yeplapp.R
import com.example.yeplapp.databinding.FragmentSearchBusinessesBinding
import com.example.yeplapp.presentation.BusinessViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBusinessesFragment : Fragment(R.layout.fragment_search_businesses) {

    private lateinit var binding: FragmentSearchBusinessesBinding
    private val viewModel by viewModels<BusinessViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBusinessesBinding.bind(view)

        viewModel.getBusinessList("pizza", 37.4, -122.399972)

        viewModel._businessList.observe(viewLifecycleOwner, Observer {
            Log.e("a", it.toString())
        })
        viewModel._isLoadingData.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })
        viewModel._notResults.observe(viewLifecycleOwner, Observer {
            binding.linearNoResults.isVisible = it
        })

    }
}