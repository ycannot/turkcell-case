package com.github.ycannot.common.composable.connectivity

sealed class ConnectionStatus {
    object Available : ConnectionStatus()
    object Unavailable : ConnectionStatus()
}