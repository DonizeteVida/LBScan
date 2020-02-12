package com.navas.lbscan.fragments.home

import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel() }
}