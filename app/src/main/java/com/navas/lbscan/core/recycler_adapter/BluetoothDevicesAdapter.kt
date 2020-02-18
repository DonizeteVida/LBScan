package com.navas.lbscan.core.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.navas.lbscan.core.entities.BDevice
import com.navas.lbscan.databinding.BluetoothRowAdapterBinding

class BluetoothDevicesAdapter(
    private var list: List<BDevice>
) : RecyclerView.Adapter<BluetoothDevicesAdapter.BluetoothDevicesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    BluetoothDevicesHolder(BluetoothRowAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BluetoothDevicesHolder, pos: Int) = holder.bindView(list[pos])

    class BluetoothDevicesHolder(
        private val binding: BluetoothRowAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bindView(device: BDevice){
            val bluetoothDevice = device.bluetoothDevice
            binding.macAddress.text = bluetoothDevice.address
            binding.name.text = bluetoothDevice.name ?: "Sem nome"
        }
    }

    fun setData(list: List<BDevice>){
        this.list = list
        notifyDataSetChanged()
    }
}