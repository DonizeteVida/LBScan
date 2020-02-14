package com.navas.lbscan.fragments.home

import com.navas.lbscan.helper.BluetoothHelper
import com.navas.lbscan.helper.LBCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeModel(private val bluetoothHelper: BluetoothHelper) {

    suspend fun searchDevices(callback: LBCallback):HomeViewState = withContext(Dispatchers.Default){
        if(bluetoothHelper.isBluetoothActived()){
            bluetoothHelper.startLBScan(callback)
            HomeViewState.StartScan
        }else{
            HomeViewState.InactiveBluetooth
        }
    }
}