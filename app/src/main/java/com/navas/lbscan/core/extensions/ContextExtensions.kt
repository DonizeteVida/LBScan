package com.navas.lbscan.core.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.work.WorkManager
import androidx.work.WorkRequest

fun Context.sendBroadCast(clazz: Class<*>){
    sendBroadcast(Intent(this, clazz))
}

fun Context.enqueue(workRequest: WorkRequest){
    WorkManager.getInstance(this).enqueue(workRequest)
}

fun Context.toast(message: String) =  Toast.makeText(this, message, Toast.LENGTH_LONG).show()