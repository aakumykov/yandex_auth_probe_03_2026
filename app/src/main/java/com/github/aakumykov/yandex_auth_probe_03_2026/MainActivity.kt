package com.github.aakumykov.yandex_auth_probe_03_2026

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.aakumykov.yandex_auth_probe_03_2026.cloud_authenticator.CloudAuthenticator
import com.github.aakumykov.yandex_auth_probe_03_2026.cloud_authenticator.YandexAuthenticator
import com.github.aakumykov.yandex_auth_probe_03_2026.databinding.ActivityMainBinding
import com.yandex.authsdk.internal.strategy.LoginType

class MainActivity : AppCompatActivity(), CloudAuthenticator.Callbacks {

    private lateinit var binding: ActivityMainBinding
    private var authToken: String? = null
    private lateinit var authenticator: CloudAuthenticator
    private val loginType: LoginType get() = LoginType.WEBVIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        authenticator = YandexAuthenticator(this, loginType, this)
        binding.authButton.setOnClickListener { startAuth() }
        binding.deauthButton.setOnClickListener { deauth() }
    }

    private fun deauth() {
        authToken = null
        binding.textView.text = ""
    }

    private fun startAuth() {
        authenticator.startAuth()
    }


    override fun onCloudAuthSuccess(authToken: String) {
        binding.textView.text = "УСПЕХ:\n\nТокен:\n$authToken"
    }

    override fun onCloudAuthFailed(throwable: Throwable) {
        binding.textView.text = "ОШИБКА:\n\n${throwable.errorMsgExtended}"
    }

    override fun onCloudAuthCancelled() {
        binding.textView.text = "Аутентификация отменена"
    }
}