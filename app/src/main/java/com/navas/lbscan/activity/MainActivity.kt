package com.navas.lbscan.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.navas.lbscan.R
import com.navas.lbscan.helper.BluetoothHelper

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}
