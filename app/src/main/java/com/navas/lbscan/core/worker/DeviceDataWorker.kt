package com.navas.lbscan.core.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class DeviceDataWorker(
    context: Context, workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        println("Worker success !")
        return Result.success()
    }
}