package com.github.aakumykov.yandex_auth_probe_03_2026.cloud_authenticator

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthResult
import com.yandex.authsdk.YandexAuthSdkContract
import com.yandex.authsdk.internal.strategy.LoginType

class YandexAuthenticator (
    componentActivity: ComponentActivity,
    private val loginType: LoginType,
    private val cloudAuthenticatorCallbacks: CloudAuthenticator.Callbacks
) : CloudAuthenticator {

    private val activityResultLauncher: ActivityResultLauncher<YandexAuthLoginOptions>

    init {
        val yandexAuthOptions = YandexAuthOptions(componentActivity, true)
        val yandexAuthSdkContract = YandexAuthSdkContract(yandexAuthOptions)

        activityResultLauncher = componentActivity.registerForActivityResult(yandexAuthSdkContract) { result: YandexAuthResult ->
            /*
            result.also {
                cloudAuthenticatorCallbacks.onCloudAuthSuccess(result)
            } ?: cloudAuthenticatorCallbacks.onCloudAuthFailed(Throwable("Результат == null"))*/
            when(result) {
                is YandexAuthResult.Success -> cloudAuthenticatorCallbacks.onCloudAuthSuccess(result.token.value)
                is YandexAuthResult.Failure -> cloudAuthenticatorCallbacks.onCloudAuthFailed(result.exception)
                else -> cloudAuthenticatorCallbacks.onCloudAuthCancelled()
            }
        }
    }

    override fun startAuth() {
        activityResultLauncher.launch(YandexAuthLoginOptions(loginType))
    }

}