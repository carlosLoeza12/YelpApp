package com.example.yeplapp.ui.bussinesdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.yeplapp.R
import com.example.yeplapp.core.addressFormat
import com.example.yeplapp.core.categoriesFormat
import com.example.yeplapp.core.hasLocationPermission
import com.example.yeplapp.databinding.FragmentBussinesDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusinessDetailsFragment : Fragment(R.layout.fragment_bussines_details), OnMapReadyCallback{

    private lateinit var binding: FragmentBussinesDetailsBinding
    private val args by navArgs<BusinessDetailsFragmentArgs>()
    private lateinit var googleMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBussinesDetailsBinding.bind(view)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setData()
    }

    private fun setData() {
        binding.business = args.currentBusiness
        binding.imgBackground.load(args.currentBusiness.image_url)
        binding.txtBusinessCategory.text = args.currentBusiness.categories.categoriesFormat()
        binding.txtBusinessAddress.text = args.currentBusiness.location.display_address.addressFormat()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            createMap(savedInstanceState)
        }
    }

    private fun createMap(savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        binding.mapView.getMapAsync(this@BusinessDetailsFragment)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {

        if (hasLocationPermission()){
            map.isMyLocationEnabled = true
        }
        googleMap = map

        // Set all the settings of the map
        with(map.uiSettings) {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
            isZoomGesturesEnabled = true
            isScrollGesturesEnabled = true
            isRotateGesturesEnabled = true
        }
        createMarker()
    }

    private fun createMarker(){
        val latitude = args.currentBusiness.coordinates.latitude
        val longitude = args.currentBusiness.coordinates.longitude

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 15f))

        googleMap.addMarker(MarkerOptions().apply {
            title(args.currentBusiness.name)
            anchor(0.5f, 0.5f)
            snippet(args.currentBusiness.alias)
            position(LatLng(latitude, longitude))
            icon(BitmapDescriptorFactory.fromResource(R.drawable.shop))
        })
    }

}