package com.navas.lbscan.fragments.home

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.navas.lbscan.R
import com.navas.lbscan.core.entities.BDevice
import com.navas.lbscan.core.extensions.byValue
import com.navas.lbscan.core.extensions.inside
import com.navas.lbscan.core.extensions.navigate
import com.navas.lbscan.core.extensions.toast
import com.navas.lbscan.core.recycler_adapter.BluetoothDevicesAdapter
import com.navas.lbscan.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), BluetoothDevicesAdapter.Callback{

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: HomeFragmentBinding

    companion object{
        private const val REQUEST_ENABLE_BLUETOOTH = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        HomeFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = this@HomeFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.homeViewState.observe(viewLifecycleOwner, homeViewStateObserver)
        viewModel.isLoading.observe(viewLifecycleOwner, isLoadingObserver)
        viewModel.lbDevices.observe(viewLifecycleOwner, lbDevicesObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        setupMenuActions(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupMenuActions(menu: Menu){
        menu.findItem(R.id.action_menu).setOnMenuItemClickListener {
            viewModel.searchDevices()
            true
        }
    }

    private val homeViewStateObserver = Observer<HomeViewState>{
        when(it){
            is HomeViewState.Initial->{

            }
            is HomeViewState.Data->{

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
        binding.recyclerView.byValue(!it)
    }

    private val lbDevicesObserver = Observer<List<BDevice>>{ devices->
        binding.recyclerView.takeIf {
            (it.adapter is BluetoothDevicesAdapter).not()
        }?.apply {
            adapter = BluetoothDevicesAdapter(mutableListOf(), this@HomeFragment)
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
                        toast("Você precisa ativar o bluetooth antes, né !!!")
                    }
                }
            }
        }
    }

    override fun onConnectRequest(device: BDevice) {
        navigate(HomeFragmentDirections.homeToDeviceInformations(device))
    }
}
