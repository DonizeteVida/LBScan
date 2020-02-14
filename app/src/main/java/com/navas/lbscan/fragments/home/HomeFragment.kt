package com.navas.lbscan.fragments.home

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.navas.lbscan.core.extensions.byValue
import com.navas.lbscan.core.recycler_adapter.BluetoothDevicesAdapter
import com.navas.lbscan.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: HomeFragmentBinding

    companion object{
        private const val REQUEST_ENABLE_BLUETOOTH = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        HomeFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = this@HomeFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            searchDevices.setOnClickListener {
                viewModel.searchDevices()
            }
        }
    }

    override fun onResume() {
        viewModel.homeViewState.observe(this, homeViewStateObserver)
        viewModel.isLoading.observe(this, isLoadingObserver)
        viewModel.lbDevices.observe(this, lbDevicesObserver)
        super.onResume()
    }

    private val homeViewStateObserver = Observer<HomeViewState>{
        when(it){
            is HomeViewState.Initial->{

            }
            is HomeViewState.Success->{

            }
            is HomeViewState.Failed->{

            }
            is HomeViewState.InactiveBluetooth->{
                val bluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(bluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
            }
        }
    }

    private val isLoadingObserver = Observer<Boolean>{
        binding.loading.root.byValue(it)
    }

    private val lbDevicesObserver = Observer<List<BluetoothDevice>>{ devices->
        binding.recyclerView.takeIf {
            val result = (it.adapter is BluetoothDevicesAdapter)
            !result
        }?.apply {
            adapter = BluetoothDevicesAdapter(mutableListOf())
        }

        (binding.recyclerView.adapter as BluetoothDevicesAdapter).setData(devices)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(resultCode){
            RESULT_OK->{
                when(requestCode){
                    REQUEST_ENABLE_BLUETOOTH->{
                        viewModel.searchDevices()
                    }
                }
            }
            RESULT_CANCELED->{
                when(requestCode){
                    REQUEST_ENABLE_BLUETOOTH->{
                        message {
                            "Você precisa ativar o bluetooth antes, né !!!"
                        }
                    }
                }
            }
        }
    }

    private fun message(m: ()-> String){
        Snackbar.make(binding.root, m(), Snackbar.LENGTH_LONG).show()
    }
}
