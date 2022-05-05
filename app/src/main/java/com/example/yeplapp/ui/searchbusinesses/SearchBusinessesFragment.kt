package com.example.yeplapp.ui.searchbusinesses
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yeplapp.R
import com.example.yeplapp.databinding.FragmentSearchBusinessesBinding
import com.example.yeplapp.presentation.BusinessViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yeplapp.data.model.Businesses
import com.example.yeplapp.ui.adapters.BusinessAdapter


@AndroidEntryPoint
class SearchBusinessesFragment : Fragment(R.layout.fragment_search_businesses), BusinessAdapter.OnBusinessClickListener {

    private lateinit var binding: FragmentSearchBusinessesBinding
    private val viewModel by viewModels<BusinessViewModel>()
    private lateinit var businessAdapter: BusinessAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBusinessesBinding.bind(view)

        viewModel._businessList.observe(viewLifecycleOwner, Observer {
           setRecycler(it.businesses)
        })
        viewModel._isLoadingData.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })
        viewModel._notResults.observe(viewLifecycleOwner, Observer {
            binding.linearNoResults.isVisible = it
        })

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.itemSearch)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    binding.recyclerBusiness.isVisible = false
                    viewModel.positionRecycler.postValue(0)
                    viewModel.getBusinessList(query, 19.3910036,-99.284044)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun setRecycler(list: List<Businesses>){
        binding.recyclerBusiness.isVisible = true
        businessAdapter = BusinessAdapter(list, this)
        binding.recyclerBusiness.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = businessAdapter
            scrollToPosition(viewModel.positionRecycler.value ?: 0)
        }
    }

    override fun onBusinessClick(businesses: Businesses, position: Int) {
        viewModel.positionRecycler.postValue(position)
        val action = SearchBusinessesFragmentDirections.actionSearchBusinessesFragmentToBussinesDetailsFragment(businesses)
        findNavController().navigate(action)
    }


}