package com.navas.lbscan.core.extensions

import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import androidx.work.WorkRequest

fun Context.sendBroadCast(clazz: Class<*>){
    sendBroadcast(Intent(this, clazz))
}

fun Context.enqueue(workRequest: WorkRequest){
    WorkManager.getInstance(this).enqueue(workRequest)
}