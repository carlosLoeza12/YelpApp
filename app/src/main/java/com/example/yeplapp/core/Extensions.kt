package com.example.yeplapp.core

import com.example.yeplapp.data.model.Categories

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