package com.navas.lbscan.fragments.device_informations

import org.koin.dsl.module

val deviceInformationsModule = module {
    factory { DeviceInformationsViewModel(get()) }
    factory { DeviceInformationsModel() }
}