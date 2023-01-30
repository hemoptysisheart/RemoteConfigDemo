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
import java.time.Duration
import java.time.Instant

class RemoteConfigLoaderImpl(
    val endpoint: Uri,
    val versionName: String,
    val versionCode: Int,
    val debug: Boolean = false
) : RemoteConfigLoader {
    companion object {
        private val TAG = RemoteConfigLoaderImpl::class.simpleName

        val CACHE_EXPIRE = Duration.ofMillis(1)
    }

    private val api: ConfigApi

    private var cachedAt: Instant = Instant.MIN
    private var cache: RemoteConfig? = null

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
        if (null == cache || Instant.now() > cachedAt.plus(CACHE_EXPIRE)) {
            Log.d(TAG, "#load cache fail.")

            val response = api.load("$versionName-$versionCode")
            cachedAt = Instant.now()
            cache = RemoteConfig(
                response.meta.let {
                    Meta(
                        it?.versionName ?: throw NullPointerException("versionName"),
                        it.versionCode ?: throw NullPointerException("versionCode"),
                        it.track ?: throw NullPointerException("track")
                    )
                },
                Payload(
                    response.payload?.featureA ?: throw NullPointerException("featureA")
                )
            )
            Log.d(TAG, "#load cached at $cachedAt")
        }

        Log.d(TAG, "#load return : $cache")
        return cache!!
    }
}
