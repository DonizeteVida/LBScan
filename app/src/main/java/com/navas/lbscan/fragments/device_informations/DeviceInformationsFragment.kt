package com.navas.lbscan.fragments.device_informations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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
}
