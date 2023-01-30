package com.github.hemoptysisheart.remoteconfigdemo.client

import android.net.Uri
import android.util.Log
import com.github.hemoptysisheart.remoteconfigdemo.client.dto.Meta
import com.github.hemoptysisheart.remoteconfigdemo.client.dto.Payload
import com.github.hemoptysisheart.remoteconfigdemo.client.dto.RemoteConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteConfigLoaderImpl(
    val endpoint: Uri,
    val versionName: String,
    val versionCode: Int,
    val debug: Boolean = false
) : RemoteConfigLoader {
    companion object {
        private val TAG = RemoteConfigLoaderImpl::class.simpleName
    }

    private val api: ConfigApi

    init {
        val builder = OkHttpClient.Builder()
        if (debug) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        api = Retrofit.Builder()
            .client(builder.build())
            .baseUrl("$endpoint/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConfigApi::class.java)
    }

    override suspend fun load(): RemoteConfig {
        Log.i(TAG, "#load called.")

        val response = api.load("$versionName-$versionCode")
        val config = RemoteConfig(
            response.meta.let {
                Meta(
                    it?.versionName ?: throw NullPointerException("versionName"),
                    it.versionCode ?: throw NullPointerException("versionCode"),
                    it.track ?: throw NullPointerException("track")
                )
            },
            response.payload.let {
                Payload(
                    it?.featureA ?: throw NullPointerException("featureA")
                )
            }
        )

        Log.i(TAG, "#load return : $config")
        return config
    }
}