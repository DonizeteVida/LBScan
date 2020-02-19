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
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

class BluetoothHelper(private val bluetoothAdapter: BluetoothAdapter) {

    private val leScanner = bluetoothAdapter.bluetoothLeScanner

    fun isBluetoothActived() = bluetoothAdapter.isEnabled

    suspend fun startLBScanAsync(): Deferred<List<BDevice>>{
        val devices = mutableListOf<BDevice>()
        val deferred = CompletableDeferred<List<BDevice>>()

        leScanner.startScan(object: ScanCallback(){
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                val device = result!!.device

                val sameDevice = devices.filter {
                    it.bluetoothDevice.address == device.address
                }
                if(sameDevice.isNotEmpty()){
                    val index = devices.indexOf(sameDevice[0])
                    devices[index] = BDevice(device, result.rssi)
                }else{
                    val newDevice = BDevice(device, result.rssi)
                    devices.add(newDevice)
                }
            }
        })
        delay(10000)

        val cancelDeferred = CompletableDeferred<String>()

        leScanner.stopScan(object: ScanCallback(){
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                cancelDeferred.complete("Complete")
            }
        })

        //println(cancelDeferred.await())

        return deferred.apply {
            complete(devices)
        }
    }

}
