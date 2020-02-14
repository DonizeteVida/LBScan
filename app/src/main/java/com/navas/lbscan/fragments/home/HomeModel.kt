package com.navas.lbscan.fragments.home

import com.navas.lbscan.helper.BluetoothHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeModel(val bluetoothHelper: BluetoothHelper) {

    suspend fun searchDevices():HomeViewState? = withContext(Dispatchers.Default){
        null
    }
}