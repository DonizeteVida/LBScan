package com.navas.lbscan.core.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("app:isVisible")
fun hideOrShow(view: View, visible: LiveData<Boolean>) = if(visible.value == true){
    view.visibility = View.VISIBLE
}else{
    view.visibility = View.GONE
}