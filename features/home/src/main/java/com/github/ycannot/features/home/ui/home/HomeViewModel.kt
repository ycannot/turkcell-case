package com.github.ycannot.features.home.ui.home


import androidx.navigation.NavController
import com.github.ycannot.common.composable.base.BaseViewModel
import com.github.ycannot.common.composable.connectivity.ConnectionStatus
import com.github.ycannot.common.composable.navigation.FeatureHomeApi
import com.github.ycannot.core.extensions.serialize
import com.github.ycannot.domain.models.GetListDataResult
import com.github.ycannot.domain.usecases.GetListDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListDataUseCase: GetListDataUseCase,
    private val featureHomeApi: FeatureHomeApi,
) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeViewEvent>(HomeContract.HomeViewState()) {

    private var connectionStatus: ConnectionStatus = ConnectionStatus.Available

    override fun sendViewEvent(event: HomeContract.HomeViewEvent) {
        when (event) {
            is HomeContract.HomeViewEvent.GetHomeData -> getHomeData()
            is HomeContract.HomeViewEvent.NavigateToDetail -> navigateToDetail(
                event.navController,
                event.product
            )

            is HomeContract.HomeViewEvent.ConnectivityStateChanged -> connectionStatusChanged(event.connectivityStatus)
        }
    }

    private fun connectionStatusChanged(connectionStatus: ConnectionStatus) {
        this.connectionStatus = connectionStatus
        updateState(viewState.value.copy(connectivityStatus = connectionStatus))
    }

    private fun getHomeData() {
        updateState(
            viewState.value.copy(
                products = listOf(),
                isLoadingVisible = true
            )
        )
        sendRequest(
            request = {
                val isOffline = connectionStatus == ConnectionStatus.Unavailable
                getListDataUseCase(GetListDataUseCase.Params(isOffline = isOffline))
            },
            onSuccess = {
                updateState(
                    viewState.value.copy(products = it.products)
                )
            },
            onError = {
                updateState(
                    viewState.value.copy(
                        error = it
                    )
                )
            }
        )
    }

    private fun navigateToDetail(
        navController: NavController,
        product: GetListDataResult.ProductResult?
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.apply {
            set(FeatureHomeApi.PARAM_PRODUCT, product.serialize())
        }
        navController.navigate(
            featureHomeApi.detailRoute()
        )
    }

}