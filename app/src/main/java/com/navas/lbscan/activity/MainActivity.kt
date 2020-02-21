package com.navas.lbscan.activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.navas.lbscan.R
import com.navas.lbscan.core.extensions.compactGetColor
import kotlinx.android.synthetic.main.activity_main.*

val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    lateinit var navController: NavController

    companion object{
        private const val REQUEST_PERMISSIONS_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        navController = findNavController(R.id.nav_host)

        navController.apply {
            addOnDestinationChangedListener(this@MainActivity)
            setupActionBarWithNavController(this)
        }

        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS_CODE)

        window.apply {
            navigationBarColor = compactGetColor(R.color.colorPrimaryDark)
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }
}

