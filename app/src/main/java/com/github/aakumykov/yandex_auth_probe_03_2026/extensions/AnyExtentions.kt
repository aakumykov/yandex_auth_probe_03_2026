package com.github.aakumykov.yandex_auth_probe_03_2026.extensions

import android.util.Log

fun Any.LogD(message: String) {
    val tag = tag()
    Log.d(tag, message)
}

fun Any.tag(): String = this.javaClass.simpleName