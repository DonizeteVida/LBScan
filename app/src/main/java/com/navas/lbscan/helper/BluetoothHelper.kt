package com.navas.lbscan.helper

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import com.navas.lbscan.core.entities.BDevice
import com.navas.lbscan.core.extensions.hasValue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

class BluetoothHelper(private val bluetoothAdapter: BluetoothAdapter) {

    private val leScanner = bluetoothAdapter.bluetoothLeScanner

    fun isBluetoothActived() = bluetoothAdapter.isEnabled

    suspend fun startLBScanAsync(): List<BDevice>{
        val devices = mutableListOf<BDevice>()

        leScanner.startScan(object: ScanCallback(){
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                val device = BDevice(result?.device, result?.rssi)

                devices.hasValue{
                    it.bluetoothDevice!!.address == device.bluetoothDevice!!.address
                }?.run {
                    bluetoothDevice = device.bluetoothDevice
                } ?: {
                    devices.add(device)
                }()
            }
        })

        delay(10000)

        return devices
    }

}
