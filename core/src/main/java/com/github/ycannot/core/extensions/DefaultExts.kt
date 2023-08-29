package com.github.ycannot.core.extensions


fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0