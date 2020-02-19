package com.navas.lbscan.core.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import com.navas.lbscan.core.extensions.enqueue
import com.navas.lbscan.core.worker.DeviceDataWorker

class DeviceDataReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val workerRequest = OneTimeWorkRequestBuilder<DeviceDataWorker>().build()
        context?.enqueue(workerRequest)
    }
}