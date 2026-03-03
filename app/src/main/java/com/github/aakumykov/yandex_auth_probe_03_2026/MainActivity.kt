package com.github.aakumykov.yandex_auth_probe_03_2026

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.aakumykov.qwerty_ktx.CloudAuthenticator
import com.github.aakumykov.qwerty_ktx.YandexAuthenticator
import com.github.aakumykov.yandex_auth_probe_03_2026.databinding.ActivityMainBinding
import com.github.aakumykov.yandex_auth_probe_03_2026.extensions.errorMsgExtended
import com.yandex.authsdk.internal.strategy.LoginType

class MainActivity : AppCompatActivity(), CloudAuthenticator.Callbacks {

    private lateinit var binding: ActivityMainBinding
    private lateinit var yandexAuthenticator: YandexAuthenticator
    private var authToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.authButton.setOnClickListener { onYandexAuthClicked() }

        yandexAuthenticator = YandexAuthenticator(this, loginType, this)
    }

    private val loginType: LoginType get() {
        return if (binding.loginTypeNative.isChecked) LoginType.NATIVE
        else LoginType.WEBVIEW
    }

    private fun onYandexAuthClicked() {
        if (null == authToken)
            yandexAuthenticator.startAuth()
        else {
            authToken = null
            binding.authTokenView.text = ""
        }
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        const val YANDEX_AUTH_TOKEN = "YANDEX_AUTH_TOKEN"
    }

    override fun onCloudAuthSuccess(authToken: String) {
        this@MainActivity.authToken = authToken
        binding.authTokenView.text = authToken
    }

    override fun onCloudAuthFailed(throwable: Throwable) {
        Toast.makeText(this, throwable.errorMsgExtended, Toast.LENGTH_LONG).show()
    }

    override fun onCloudAuthCancelled() {
        Toast.makeText(this, "Авторизация отменена", Toast.LENGTH_LONG).show()
    }
}