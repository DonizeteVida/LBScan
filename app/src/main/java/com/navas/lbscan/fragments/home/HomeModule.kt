package com.navas.lbscan.fragments.home

import android.bluetooth.BluetoothManager
import android.content.Context
import com.navas.lbscan.helper.BluetoothHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel(get()) }
    factory { HomeModel(get()) }
    factory { BluetoothHelper(
        (androidContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    )}
}