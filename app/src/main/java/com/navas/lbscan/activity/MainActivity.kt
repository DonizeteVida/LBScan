package com.navas.lbscan.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.navas.lbscan.R
import com.navas.lbscan.core.broadcast.DeviceDataReceiver
import com.navas.lbscan.core.extensions.sendBroadCast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val context: Context by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        upActionBar()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host).navigateUp()

    private fun AppCompatActivity.upActionBar() = NavigationUI.setupActionBarWithNavController(this, findNavController( R.id.nav_host))

    override fun onResume() {
        sendBroadCast(DeviceDataReceiver::class.java)
        super.onResume()
    }
}
