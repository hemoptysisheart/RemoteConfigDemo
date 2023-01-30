package com.github.hemoptysisheart.remoteconfigdemo.client

import com.github.hemoptysisheart.remoteconfigdemo.client.response.ConfigResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ConfigApi {
    @GET("config/{filename}.json")
    suspend fun load(@Path("filename") filename: String): ConfigResponse
}