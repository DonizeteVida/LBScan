package com.navas.lbscan.activity

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.navas.lbscan.R
import com.navas.lbscan.core.broadcast.DeviceDataReceiver
import com.navas.lbscan.core.extensions.sendBroadCast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    var control = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        upActionBar()
        findNavController(R.id.nav_host).apply {
            addOnDestinationChangedListener(this@MainActivity)
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host).navigateUp()

    private fun AppCompatActivity.upActionBar() = NavigationUI.setupActionBarWithNavController(this, findNavController( R.id.nav_host))

    override fun onResume() {
        sendBroadCast(DeviceDataReceiver::class.java)
        super.onResume()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        control = !control

        if(control){
            AnimationUtils.loadAnimation(this, R.anim.right_to_center).also {
                toolbar.startAnimation(it)
            }
        }else{
            AnimationUtils.loadAnimation(this, R.anim.center_to_left).also {
                toolbar.startAnimation(it)
            }
        }
    }
}
