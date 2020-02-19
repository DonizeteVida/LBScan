package com.navas.lbscan.core.entities

import android.bluetooth.BluetoothDevice
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BDevice(
    val bluetoothDevice: BluetoothDevice,
    val rssi: Int
) : Parcelable