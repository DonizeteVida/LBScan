package com.navas.lbscan.fragments.home

import com.navas.lbscan.helper.BluetoothHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeModel(private val bluetoothHelper: BluetoothHelper) {

    suspend fun searchDevices(): HomeViewState = withContext(Dispatchers.Default){
        if(bluetoothHelper.isBluetoothActived()) {
            val devices = bluetoothHelper.startLBScanAsync().await()
            HomeViewState.Data(devices)
        }else{
            HomeViewState.InactiveBluetooth
        }
    }
}