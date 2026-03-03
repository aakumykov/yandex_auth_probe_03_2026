package com.github.aakumykov.yandex_auth_probe_03_2026.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(@StringRes strRes: Int) {
    showToast(getString(strRes))
}

@SuppressLint("ApplySharedPref")
fun Activity.storeStringInPreferences(key: String, value: String) {
    PreferenceManager.getDefaultSharedPreferences(this).edit()
        .putString(key, value)
        .commit()
}

@SuppressLint("ApplySharedPref")
fun Activity.eraseStringFromPreferences(key: String) {
    PreferenceManager.getDefaultSharedPreferences(this).edit()
        .putString(key, null)
        .commit()
}

fun Activity.getStringFromPreferences(key: String): String? {
    return PreferenceManager.getDefaultSharedPreferences(this)
        .getString(key, null)
}