package com.github.hemoptysisheart.remoteconfigdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.remoteconfigdemo.client.RemoteConfigLoader
import com.github.hemoptysisheart.remoteconfigdemo.client.dto.RemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteConfigLoader: RemoteConfigLoader
) : ViewModel() {
    companion object {
        private val TAG = MainViewModel::class.simpleName
    }

    val remoteConfig = MutableStateFlow<RemoteConfig?>(null)

    init {
        viewModelScope.launch {
            remoteConfig.emit(remoteConfigLoader.load())
        }
    }
}
