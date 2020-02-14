package com.navas.lbscan.fragments.home

sealed class HomeViewState {
    object Initial : HomeViewState()
    data class Success(val data: Any) : HomeViewState()
    data class Failed(val error: Throwable) : HomeViewState()
}