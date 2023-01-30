package com.github.hemoptysisheart.remoteconfigdemo.client.response

data class ConfigResponse(
    var meta: MetaResponse? = null,
    var payload: PayloadResponse? = null
)
