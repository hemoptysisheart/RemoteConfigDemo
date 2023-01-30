package com.github.hemoptysisheart.remoteconfigdemo

import android.app.Application
import android.util.Log
import com.github.hemoptysisheart.remoteconfigdemo.client.RemoteConfigLoader
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class RemoteConfigDemoApplication : Application() {
    companion object {
        private val TAG = RemoteConfigDemoApplication::class.simpleName
    }

    @Inject
    lateinit var remoteConfigLoader: RemoteConfigLoader

    override fun onCreate() {
        Log.i(TAG, "#onCreate called.")
        super.onCreate()

        MainScope().launch {
            val remoteConfig = remoteConfigLoader.load()
            Log.i(TAG, "#onCreate : remoteConfig=$remoteConfig")
        }
    }
}
