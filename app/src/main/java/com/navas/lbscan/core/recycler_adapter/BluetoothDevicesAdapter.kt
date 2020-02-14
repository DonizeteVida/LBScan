package com.navas.lbscan.core.recycler_adapter

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.navas.lbscan.databinding.BluetoothRowAdapterBinding

class BluetoothDevicesAdapter(
    private var list: List<BluetoothDevice>
) : RecyclerView.Adapter<BluetoothDevicesAdapter.BluetoothDevicesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    BluetoothDevicesHolder(BluetoothRowAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BluetoothDevicesHolder, pos: Int) = holder.bindView(list[pos])

    class BluetoothDevicesHolder(
        private val binding: BluetoothRowAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bindView(device: BluetoothDevice){
            binding.macAddress.text = device.address
        }
    }

    fun setData(list: List<BluetoothDevice>){
        this.list = list
        notifyDataSetChanged()
    }
}