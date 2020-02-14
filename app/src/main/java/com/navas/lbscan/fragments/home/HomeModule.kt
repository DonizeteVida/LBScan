package com.navas.lbscan.fragments.home

import com.navas.lbscan.helper.BluetoothHelper
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel(get()) }
    factory { HomeModel(get()) }
    factory { BluetoothHelper(get()) }
}