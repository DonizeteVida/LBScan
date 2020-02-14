package com.navas.lbscan.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val homeModel: HomeModel) : ViewModel() {
    private val _homeViewState = MutableLiveData<HomeViewState>().apply {
        postValue(HomeViewState.Initial)
    }
    val homeViewState:LiveData<HomeViewState> get() = _homeViewState

    fun searchDevices() = viewModelScope.launch {
        try{
            val state = homeModel.searchDevices()
            _homeViewState.postValue(state)
        }catch (t: Throwable){
            _homeViewState.postValue(HomeViewState.Failed(t))
        }
    }
}
