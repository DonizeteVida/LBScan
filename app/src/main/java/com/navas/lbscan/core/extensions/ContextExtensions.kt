package com.navas.lbscan.core.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.work.WorkManager
import androidx.work.WorkRequest

fun Context.sendBroadCast(clazz: Class<*>, bundle: Bundle = Bundle()){
    sendBroadcast(Intent(this, clazz).putExtras(bundle))
}

fun Context.enqueue(workRequest: WorkRequest){
    WorkManager.getInstance(this).enqueue(workRequest)
}

fun Context.toast(message: String) =  Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.compactGetColor(color: Int) = ContextCompat.getColor(this, color)