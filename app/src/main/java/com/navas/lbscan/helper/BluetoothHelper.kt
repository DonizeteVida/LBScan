package com.navas.lbscan.helper

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager

class BluetoothHelper(private val context: Context) {

    private val bluetoothAdapter: BluetoothAdapter = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter

    suspend fun isBluetoothActived() = hasLowBluetooth() && bluetoothAdapter.isEnabled

    private suspend fun hasLowBluetooth() = context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
}