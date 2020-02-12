package com.navas.lbscan.core.application

import android.app.Application
import com.navas.lbscan.fragments.home.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LBScanApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LBScanApplication)
            modules(
                listOf(
                    homeModule
                )
            )
        }
    }
}