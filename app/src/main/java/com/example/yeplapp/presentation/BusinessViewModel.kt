package com.example.yeplapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yeplapp.data.model.Response
import com.example.yeplapp.repository.BusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(private val repository: BusinessRepository) : ViewModel() {

    private val businessList = MutableLiveData<Response>()
    val _businessList: LiveData<Response>
        get() = businessList

    private val isLoadingData = MutableLiveData<Boolean>()
    val _isLoadingData: LiveData<Boolean>
        get() = isLoadingData

    private val notResults = MutableLiveData<Boolean>()
    val _notResults: LiveData<Boolean>
        get() = notResults

    fun getBusinessList(term: String, latitude: Double, longitude: Double){
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingData.postValue(true)
            notResults.postValue(false)
            try {
                val list = repository.getBusinessList(term = term, latitude = latitude, longitude = longitude)
                if(!list.businesses.isNullOrEmpty()){
                    isLoadingData.postValue(false)
                    businessList.postValue(list)
                }else{
                    isLoadingData.postValue(false)
                    notResults.postValue(true)
                }
            }catch (e: Exception){
                isLoadingData.postValue(false)
                notResults.postValue(true)
            }
        }
    }

}