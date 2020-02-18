package com.navas.lbscan.core.entities

import android.bluetooth.BluetoothDevice

data class BDevice(
    val bluetoothDevice: BluetoothDevice,
    var scanCount : Int = 0
)