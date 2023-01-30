package com.github.hemoptysisheart.remoteconfigdemo.client.response

import com.github.hemoptysisheart.remoteconfigdemo.client.dto.ReleaseTrack

data class MetaResponse(
    var versionName: String? = null,
    var versionCode: Int? = null,
    var track: ReleaseTrack? = null
)
