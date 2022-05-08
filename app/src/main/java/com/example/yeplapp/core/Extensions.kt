package com.example.yeplapp.core

import androidx.fragment.app.Fragment
import com.example.yeplapp.R
import com.example.yeplapp.application.AppConstants
import com.example.yeplapp.data.model.Categories
import com.vmadalin.easypermissions.EasyPermissions

fun List<String>.addressFormat(): String {
    var addressList = ""
    this.forEach() {
        addressList = "$addressList$it. "
    }
    return addressList
}

fun List<Categories>.categoriesFormat(): String{
    var categories = ""
    this.forEach {
        categories = categories + it.title + ". "
    }
    return categories
}

fun Fragment.hasLocationPermission(): Boolean {
    return EasyPermissions.hasPermissions(
        this.requireContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
}

fun Fragment.requestLocationPermission() {
    EasyPermissions.requestPermissions(
        this,
        getString(R.string.search_permissions),
        AppConstants.PERMISSION_LOCATION_REQUEST_CODE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
}