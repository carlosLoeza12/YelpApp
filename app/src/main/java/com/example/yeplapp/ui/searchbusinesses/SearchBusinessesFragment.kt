package com.example.yeplapp.ui.searchbusinesses
import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.yeplapp.core.hasLocationPermission
import com.example.yeplapp.core.requestLocationPermission
import com.example.yeplapp.data.model.Businesses
import com.example.yeplapp.ui.adapters.BusinessAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

@AndroidEntryPoint
class SearchBusinessesFragment : Fragment(R.layout.fragment_search_businesses),
    BusinessAdapter.OnBusinessClickListener, EasyPermissions.PermissionCallbacks {

    private lateinit var binding: FragmentSearchBusinessesBinding
    private val viewModel by viewModels<BusinessViewModel>()
    private lateinit var businessAdapter: BusinessAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var business =""

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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.itemSearch)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && hasLocationPermission()) {
                    binding.recyclerBusiness.isVisible = false
                    viewModel.positionRecycler.postValue(0)
                    doSearchBusiness(query)
                }else{
                    business = query ?:"Restaurantes"
                    requestLocationPermission()
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
        val action = SearchBusinessesFragmentDirections
            .actionSearchBusinessesFragmentToBussinesDetailsFragment(businesses)
        findNavController().navigate(action)
    }

    //Permission

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        doSearchBusiness(business)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            this.requestLocationPermission()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions,grantResults, this)
    }

    @SuppressLint("MissingPermission")
    private fun doSearchBusiness(business: String){
        //get location
        if(hasLocationPermission()){
            fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity())  { location ->
                if(location != null){
                    viewModel.getBusinessList(business, location.latitude,location.longitude)
                }
            }
        }else{
            requestLocationPermission()
        }
    }

}