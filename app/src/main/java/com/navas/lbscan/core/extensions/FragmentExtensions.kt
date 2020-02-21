package com.navas.lbscan.core.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.AnimBuilder
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.navas.lbscan.R

private val navOptions = NavOptions.Builder().apply{
    setEnterAnim(R.anim.right_to_center)
    setExitAnim(R.anim.center_to_left)
    setPopEnterAnim(R.anim.left_to_center)
    setPopExitAnim(R.anim.center_to_right)
}.build()

fun Fragment.navigate(directions: NavDirections, options: NavOptions = navOptions) =
    findNavController().navigate(directions, options)

fun Fragment.toast(message: String) = context?.toast(message)