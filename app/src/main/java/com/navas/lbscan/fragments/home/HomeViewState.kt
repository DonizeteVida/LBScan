package com.navas.lbscan.fragments.home

sealed class HomeViewState {

    object Initial : HomeViewState()
    object InactiveBluetooth: HomeViewState()
    object StartScan: HomeViewState()
    data class Success(val data: Any) : HomeViewState()
    data class Failed(val error: Throwable) : HomeViewState()
}