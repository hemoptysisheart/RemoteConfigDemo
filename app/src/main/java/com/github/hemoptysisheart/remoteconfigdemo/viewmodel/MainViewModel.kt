package com.github.hemoptysisheart.remoteconfigdemo.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    companion object {
        private val TAG = MainViewModel::class.simpleName
    }
}
