package com.github.ycannot.common.composable.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ycannot.core.helpers.DispatchGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ViewState, EVENT: ViewEvent>(initialState: STATE) : ViewModel() {


    private val _viewState = MutableStateFlow(initialState)
    val viewState: StateFlow<STATE> = _viewState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = initialState
        )

    private val loadingDispatchGroup = DispatchGroup(runnable = {
        updateState(viewState.value.apply { isLoadingVisible = false } )
    })


    open fun sendViewEvent(event: EVENT) {}

    fun updateState(state: STATE){
        _viewState.tryEmit(state)
    }

    private fun clearPreviousError(){
        _viewState.tryEmit(viewState.value.apply { error = null })
    }

    fun <T : Any?> sendRequest(
        request: suspend () -> T,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        complete: (() -> Unit)? = null,
    ) = viewModelScope.launch {
        clearPreviousError()
        try {
            showLoading()
            val result = request.invoke()
            onSuccess?.invoke(result)
        } catch (ex: Exception) {
            onError?.invoke(ex) //?: handleException(ex)
        }
        hideLoading()
        complete?.invoke()
    }


    fun showLoading() {
        loadingDispatchGroup.enter()
    }

    fun hideLoading() {
        loadingDispatchGroup.leave()
    }

    fun hideLoadingForce() {
        loadingDispatchGroup.reset()
        loadingDispatchGroup.leave()
    }
}
