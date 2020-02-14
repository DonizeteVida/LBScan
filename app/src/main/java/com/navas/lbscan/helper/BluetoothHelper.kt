package com.navas.lbscan.helper

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class BluetoothHelper(private val context: Context) {


    private val bluetoothAdapter: BluetoothAdapter = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter

    suspend fun isBluetoothActived() = hasLowBluetooth() && bluetoothAdapter.isEnabled

    private suspend fun hasLowBluetooth() = context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

    public suspend fun startLBScan(callback: LBCallback){
        bluetoothAdapter.startLeScan{device,rssi,scanRecord->
            callback(device, rssi, scanRecord)
        }
    }

    public suspend fun stopLBScan() = bluetoothAdapter.stopLeScan(null)
}

typealias LBCallback = (BluetoothDevice, Int, ByteArray)->Unit

