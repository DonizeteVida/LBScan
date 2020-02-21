package com.navas.lbscan.core.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.navas.lbscan.core.entities.BDevice
import com.navas.lbscan.core.service.DeviceDataService

class DeviceDataReceiver : BroadcastReceiver() {
    companion object{
        const val BDEVICE = "DEVICE"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val device = intent?.getParcelableExtra<BDevice>(BDEVICE)

        context?.startService(
            Intent(context, DeviceDataService::class.java).putExtras(
                Bundle().apply {
                    putParcelable(BDEVICE, device)
                }
            )
        )
    }
}