package com.navas.lbscan.fragments.device_informations

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.navas.lbscan.core.broadcast.DeviceDataReceiver
import com.navas.lbscan.core.broadcast.DeviceDataReceiver.Companion.BDEVICE
import com.navas.lbscan.core.extensions.sendBroadCast
import com.navas.lbscan.core.extensions.toast
import com.navas.lbscan.core.service.DeviceDataService
import com.navas.lbscan.core.service.DeviceDataService.Companion.DEVICE_DATA_SERVICE_CONNECTED
import com.navas.lbscan.core.service.DeviceDataService.Companion.DEVICE_DATA_SERVICE_DISCONNECTED
import com.navas.lbscan.core.service.DeviceDataService.Companion.DEVICE_DATA_SERVICE_RECEIVE_DATA
import com.navas.lbscan.core.service.DeviceDataService.Companion.DEVICE_DATA_SERVICE_RSSI
import com.navas.lbscan.databinding.DeviceInformationsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceInformationsFragment : Fragment() {

    private val viewModel: DeviceInformationsViewModel by viewModel()

    private val args: DeviceInformationsFragmentArgs by navArgs()

    lateinit var binding: DeviceInformationsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DeviceInformationsFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onResume() {
        context?.registerReceiver(bluetoothDeviceReceiver, IntentFilter().apply {
            addAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            addAction(DEVICE_DATA_SERVICE_DISCONNECTED)
            addAction(DEVICE_DATA_SERVICE_CONNECTED)
            addAction(DEVICE_DATA_SERVICE_RSSI)
            priority = 1
        })

        context?.sendBroadCast(DeviceDataReceiver::class.java, Bundle().apply {
            putParcelable(BDEVICE, args.device)
        })
        super.onResume()
    }

    override fun onDestroy() {
        context?.unregisterReceiver(bluetoothDeviceReceiver)
        context?.stopService(Intent(context, DeviceDataService::class.java))
        super.onDestroy()
    }
    private val bluetoothDeviceReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action ?: ""
            when(action){
                DEVICE_DATA_SERVICE_CONNECTED->{
                    toast("Você conseguiu conectar-se !!!")
                }

                DEVICE_DATA_SERVICE_DISCONNECTED->{
                    toast("O serviço foi desconectado !!!")
                }

                DEVICE_DATA_SERVICE_RECEIVE_DATA->{
                    toast("Recebemos algo, daqui a pouco implementamos !!!")
                }

                DEVICE_DATA_SERVICE_RSSI->{
                    toast("RSSI: ${intent?.extras?.getString("rssi")}")
                }
            }
        }
    }
}
