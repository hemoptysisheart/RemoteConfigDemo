package com.github.hemoptysisheart.remoteconfigdemo.client.dto

enum class ReleaseTrack(
    val test: Boolean
) {
    INTERNAL(true),
    CLOSED(true),
    OPEN(true),
    PRODUCTION(false);
}
