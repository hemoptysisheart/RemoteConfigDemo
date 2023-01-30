package com.github.hemoptysisheart.remoteconfigdemo.configuration

import android.net.Uri
import android.util.Log
import com.github.hemoptysisheart.remoteconfigdemo.BuildConfig
import com.github.hemoptysisheart.remoteconfigdemo.BuildConfig.REMOTE_CONFIG_ENDPOINT
import com.github.hemoptysisheart.remoteconfigdemo.client.RemoteConfigLoader
import com.github.hemoptysisheart.remoteconfigdemo.client.RemoteConfigLoaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleConfiguration {
    companion object {
        private val TAG = ModuleConfiguration::class.simpleName
    }

    @Provides
    @Singleton
    fun provideRemoteConfigLoader(): RemoteConfigLoader {
        val loader = RemoteConfigLoaderImpl(
            Uri.parse(REMOTE_CONFIG_ENDPOINT),
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE,
            BuildConfig.DEBUG
        )

        Log.i(TAG, "#provideRemoteConfigLoader return : $loader")
        return loader
    }
}
