package com.navas.lbscan.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navas.lbscan.core.entities.BDevice
import com.navas.lbscan.core.extensions.init
import com.navas.lbscan.core.extensions.pv
import kotlinx.coroutines.launch

class HomeViewModel(private val homeModel: HomeModel) : ViewModel() {

    private val _homeViewState = MutableLiveData<HomeViewState>().init(HomeViewState.Initial)
    private val _lbDevices = MutableLiveData<List<BDevice>>().init(mutableListOf())
    private val _isLoading = MutableLiveData<Boolean>().init(false)

    val homeViewState:LiveData<HomeViewState> get() = _homeViewState
    val lbDevices: LiveData<List<BDevice>> get() = _lbDevices
    val isLoading:LiveData<Boolean> get() = _isLoading

    fun searchDevices() = viewModelScope.launch {
        _isLoading.pv(true)

        try{
            when(val state = homeModel.searchDevices()){
                is HomeViewState.Data->{
                    _lbDevices.pv(state.data)
                }
                is HomeViewState.InactiveBluetooth->{
                    _homeViewState.pv(state)
                }
            }

        }catch (t: Throwable){
            _homeViewState.pv(HomeViewState.Failed(t))
        }finally {
            _isLoading.pv(false)
        }
    }
}
