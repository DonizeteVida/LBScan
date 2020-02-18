package com.navas.lbscan.fragments.home

import com.navas.lbscan.core.entities.BDevice

sealed class HomeViewState {

    object Initial : HomeViewState()
    object InactiveBluetooth: HomeViewState()
    object StartScan: HomeViewState()
    data class Data(val data: List<BDevice>) : HomeViewState()
    data class Failed(val error: Throwable) : HomeViewState()
}