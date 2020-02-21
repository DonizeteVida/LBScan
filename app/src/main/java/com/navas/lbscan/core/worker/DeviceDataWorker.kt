package com.navas.lbscan.core.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class DeviceDataWorker(
    val context: Context, workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        return Result.success()
    }
}