package com.github.hemoptysisheart.remoteconfigdemo.client

import com.github.hemoptysisheart.remoteconfigdemo.client.dto.RemoteConfig

interface RemoteConfigLoader {
    suspend fun load(): RemoteConfig
}