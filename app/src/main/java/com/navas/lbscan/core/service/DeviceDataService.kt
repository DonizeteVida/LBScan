package com.navas.lbscan.core.service

import android.app.Service
import android.bluetooth.*
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import com.navas.lbscan.core.broadcast.DeviceDataReceiver
import com.navas.lbscan.core.entities.BDevice
import kotlinx.coroutines.*

class DeviceDataService: Service(){
    companion object{
        private val TAG = DeviceDataService::class.java.simpleName

        const val DEVICE_DATA_SERVICE_RECEIVE_DATA = "com.navas.lbscan.ON_DATA_RECEIVED"
        const val DEVICE_DATA_SERVICE_CONNECTED = "com.navas.lbscan.CONNECTED"
        const val DEVICE_DATA_SERVICE_DISCONNECTED = "com.navas.lbscan.DISCONNECTED"
        const val DEVICE_DATA_SERVICE_RSSI = "com.navas.lbscan.RSSI"
    }

    private var bluetoothGatt: BluetoothGatt? = null
    val job = Job()

    private val bluetoothGattCallback = object: BluetoothGattCallback(){
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            when(newState){
                BluetoothProfile.STATE_CONNECTED->{
                    sendAction(DEVICE_DATA_SERVICE_CONNECTED)
                    requestDatas()
                }

                BluetoothProfile.STATE_DISCONNECTED->{
                    sendAction(DEVICE_DATA_SERVICE_DISCONNECTED)
                }
            }
            super.onConnectionStateChange(gatt, status, newState)
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onCharacteristicRead(gatt, characteristic, status)
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onCharacteristicChanged(gatt, characteristic)
        }

        override fun onDescriptorRead(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onDescriptorRead(gatt, descriptor, status)
        }


        override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onMtuChanged(gatt, mtu, status)
        }

        override fun onPhyRead(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onPhyRead(gatt, txPhy, rxPhy, status)
        }

        override fun onPhyUpdate(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onPhyUpdate(gatt, txPhy, rxPhy, status)
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            sendAction(DEVICE_DATA_SERVICE_RECEIVE_DATA)
            super.onServicesDiscovered(gatt, status)
        }

        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            sendAction(DEVICE_DATA_SERVICE_RSSI, Bundle().apply {
                putString("rssi", rssi.toString())
            })
            super.onReadRemoteRssi(gatt, rssi, status)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val device = intent?.getParcelableExtra<BDevice>(DeviceDataReceiver.BDEVICE)
        bluetoothGatt = device?.bluetoothDevice?.connectGatt(this, true, bluetoothGattCallback)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?):IBinder? = null

    override fun onDestroy() {
        bluetoothGatt?.disconnect()
        job.cancel()
        super.onDestroy()
    }

    private fun sendAction(action: String, bundle: Bundle = Bundle()){
        sendBroadcast(Intent(action).putExtras(bundle))
    }

    fun requestDatas() = CoroutineScope(Dispatchers.IO + job).also {
        it.launch {
            while(true){
                bluetoothGatt?.readRemoteRssi()
                delay(2000)
            }
        }
    }
}
