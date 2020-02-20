package com.navas.lbscan.core.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(directions: NavDirections){
    findNavController().navigate(directions)
}

fun Fragment.toast(message: String){
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}