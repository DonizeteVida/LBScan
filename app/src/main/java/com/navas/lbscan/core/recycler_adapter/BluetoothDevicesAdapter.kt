package com.navas.lbscan.core.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.navas.lbscan.core.entities.BDevice
import com.navas.lbscan.databinding.BluetoothRowAdapterBinding

class BluetoothDevicesAdapter(
    private var list: List<BDevice>,
    private val callback: Callback
) : RecyclerView.Adapter<BluetoothDevicesAdapter.BluetoothDevicesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    BluetoothDevicesHolder(BluetoothRowAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BluetoothDevicesHolder, pos: Int) = holder.bindView(list[pos])

    inner class BluetoothDevicesHolder(private val binding: BluetoothRowAdapterBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(device: BDevice){

            device.apply {
                binding.macAddress.text = device.bluetoothDevice.address
                binding.name.text = device.bluetoothDevice.name ?: "Sem nome"
                binding.strength.text = "${device.rssi}"

                binding.apply {
                    connectButton.setOnClickListener {
                        callback.onConnectRequest(device)
                    }
                }
            }

        }

    }

    fun setData(list: List<BDevice>){
        val orderedList = list.sortedByDescending {
            it.rssi
        }
        this.list = orderedList
        notifyDataSetChanged()
    }

    interface Callback{
        fun onConnectRequest(device: BDevice)
    }
}