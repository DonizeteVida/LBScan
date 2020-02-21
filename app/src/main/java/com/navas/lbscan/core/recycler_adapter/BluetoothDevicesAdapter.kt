package com.navas.lbscan.core.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.navas.lbscan.R
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

            binding.macValue.text = device.bluetoothDevice?.address
            binding.nameValue.text = device.bluetoothDevice?.name ?: "Sem nome"
            binding.rssiValue.text = "${device.rssi}"
            binding.rssiContainer.apply {
                background = resources.getDrawable(getDrawable(device.rssi ?: 0), null)
            }
            binding.root.setOnClickListener {
                callback.onConnectRequest(device)
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

    fun getDrawable(strength: Int): Int = when(strength){
        in 0 downTo -30-> R.drawable.rssi_green
        in -31 downTo -40->R.drawable.rssi_yellow
        else-> R.drawable.rssi_red
    }


}