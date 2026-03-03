package com.github.aakumykov.yandex_auth_probe_03_2026.extensions

val Throwable.errorMsg: String get() = message ?: javaClass.name

val Throwable.errorMsgExtended: String get() = message?.let { "$it (${javaClass.name})" } ?: javaClass.name