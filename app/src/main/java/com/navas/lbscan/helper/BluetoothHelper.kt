package com.navas.lbscan.helper

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import com.navas.lbscan.core.entities.BDevice
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

class BluetoothHelper(private val context: Context) {


    private val bluetoothAdapter: BluetoothAdapter = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter

    suspend fun isBluetoothActived() = hasLowBluetooth() && bluetoothAdapter.isEnabled

    private suspend fun hasLowBluetooth() = context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

    suspend fun startLBScanAsync(): Deferred<List<BDevice>>{
        val devices = mutableListOf<BDevice>()
        val deferred = CompletableDeferred<List<BDevice>>()

        bluetoothAdapter.startLeScan{device,rssi,scanRecord->
            val foundedDevices = devices.filter{it.bluetoothDevice.address == device.address}
            if(foundedDevices.isNotEmpty()){
                foundedDevices[0].scanCount++
            }else{
                val newDevice = BDevice(device)
                devices.add(newDevice)
            }
        }
        delay(10000)
        stopLBScan(null)
        return deferred.apply {
            complete(devices)
        }
    }

    private fun stopLBScan(block: BluetoothAdapter.LeScanCallback?) =
        bluetoothAdapter.stopLeScan(block)
}
