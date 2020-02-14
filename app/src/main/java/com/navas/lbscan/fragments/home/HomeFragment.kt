package com.navas.lbscan.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.navas.lbscan.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        HomeFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchDevices.setOnClickListener {
            viewModel.searchDevices()
        }
        viewModel.homeViewState.observe(this, homeViewStateObserver)
    }

    private val homeViewStateObserver = Observer<HomeViewState>{
        when(it){
            is HomeViewState.Initial->{

            }
            is HomeViewState.Success->{

            }
            is HomeViewState.Failed->{

            }
        }
    }
}
