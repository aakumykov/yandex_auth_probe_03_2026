package com.github.aakumykov.yandex_auth_probe_03_2026.cloud_authenticator

interface CloudAuthenticator {

    fun startAuth()

    interface Callbacks {
        fun onCloudAuthSuccess(authToken: String)
        fun onCloudAuthFailed(throwable: Throwable)
        fun onCloudAuthCancelled()
    }
}