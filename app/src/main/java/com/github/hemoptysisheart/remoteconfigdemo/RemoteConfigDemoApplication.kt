package com.github.hemoptysisheart.remoteconfigdemo

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RemoteConfigDemoApplication:Application() {
    companion object{
        private val TAG = RemoteConfigDemoApplication::class.simpleName
    }

    override fun onCreate() {
        Log.i(TAG, "#onCreate called.")
        super.onCreate()
    }
}
