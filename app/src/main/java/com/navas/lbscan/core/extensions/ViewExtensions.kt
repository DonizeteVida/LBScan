package com.navas.lbscan.core.extensions

import android.view.View

fun View.on() = apply{
    visibility = View.VISIBLE
}

fun View.off() = apply{
    visibility = View.GONE
}

fun View.byValue(value: Boolean) = if(value){
    on()
}else{
    off()
}