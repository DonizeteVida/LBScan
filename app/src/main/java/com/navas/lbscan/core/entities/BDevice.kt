package com.navas.lbscan.core.entities

import android.bluetooth.BluetoothDevice
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BDevice(
    var bluetoothDevice: BluetoothDevice?,
    var rssi: Int?
) : Parcelable{
    override fun equals(other: Any?) = if(other is BDevice){
        (other.bluetoothDevice?.address == bluetoothDevice?.address)
    }else{
        false
    }
}