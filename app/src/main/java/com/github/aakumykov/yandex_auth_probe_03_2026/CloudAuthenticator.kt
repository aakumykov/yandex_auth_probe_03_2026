package com.github.aakumykov.qwerty_ktx

interface CloudAuthenticator {

    fun startAuth()

    interface Callbacks {
        fun onCloudAuthSuccess(authToken: String)
        fun onCloudAuthFailed(throwable: Throwable)
        fun onCloudAuthCancelled()
    }
}