package com.github.ycannot.features.home.ui.home

import androidx.navigation.NavController
import com.github.ycannot.common.composable.base.ViewEvent
import com.github.ycannot.common.composable.base.ViewState
import com.github.ycannot.common.composable.connectivity.ConnectionStatus
import com.github.ycannot.domain.models.GetListDataResult

class HomeContract {
    data class HomeViewState(
        override var isLoadingVisible: Boolean = true,
        override var error: Exception? = null,
        var products: List<GetListDataResult.ProductResult> = listOf(),
        var isPullRefreshOnProgress: Boolean = false,
        val connectivityStatus: ConnectionStatus = ConnectionStatus.Available
    ) : ViewState

    sealed class HomeViewEvent : ViewEvent {
        data class ConnectivityStateChanged(val connectivityStatus: ConnectionStatus) : HomeViewEvent()
        object GetHomeData : HomeViewEvent()
        data class NavigateToDetail(
            val navController: NavController,
            val product: GetListDataResult.ProductResult?
        ) : HomeViewEvent()
    }

}
