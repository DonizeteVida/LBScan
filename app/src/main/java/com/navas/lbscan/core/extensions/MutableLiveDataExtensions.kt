package com.navas.lbscan.core.extensions

import androidx.lifecycle.MutableLiveData

fun <T>MutableLiveData<T>.init(value: T) = apply {
    postValue(value)
}

fun <T>MutableLiveData<T>.pv(value: T) = postValue(value)
fun <T>MutableLiveData<T>.sv(value: T) = setValue(value)