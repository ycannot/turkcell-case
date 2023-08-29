package com.github.ycannot.core.helpers


class DispatchGroup(
    private var count: Int = 0,
    private inline var runnable: (() -> Unit)? = null
) {

    @Synchronized
    fun enter() {
        count++
    }

    @Synchronized
    fun leave() {
        if (count > 0) {
            count--
        }
        notifyGroup()
    }

    @Synchronized
    fun reset() {
        count = 0
    }

    private fun notifyGroup() {
        if (count <= 0) {
            runnable?.invoke()
        }
    }
}