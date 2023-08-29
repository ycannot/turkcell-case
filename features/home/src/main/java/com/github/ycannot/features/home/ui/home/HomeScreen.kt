package com.github.ycannot.features.home.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.ycannot.common.composable.TopBar
import com.github.ycannot.common.composable.connectivity.ConnectionStatus
import com.github.ycannot.common.composable.connectivity.connectivityState
import com.github.ycannot.common.composable.theme.GreyAppBar
import com.github.ycannot.common.composable.theme.TextGray16Sp
import com.github.ycannot.common.composable.theme.TextWhite20Sp
import com.github.ycannot.common.composable.theme.TtechCaseTheme
import com.github.ycannot.common.composable.theme.White50
import com.github.ycannot.common.composable.views.CenteredCircularProgressIndicator
import com.github.ycannot.common.composable.views.ErrorMessage
import com.github.ycannot.domain.models.GetListDataResult
import com.github.ycannot.features.home.R
import com.github.ycannot.features.home.views.gridItem.ProductGridItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    vm: HomeViewModel = hiltViewModel()
) {
    val columnCount = getColumnCount()
    val state by vm.viewState.collectAsState()
    val connectivityState by connectivityState()

    LaunchedEffect(key1 = connectivityState) {
        if (connectivityState != state.connectivityStatus)
            vm.sendViewEvent(HomeContract.HomeViewEvent.ConnectivityStateChanged(connectivityState))

    }

    LaunchedEffect(key1 = vm) {
        vm.sendViewEvent(HomeContract.HomeViewEvent.GetHomeData)
    }

    Scaffold(
        modifier = modifier.background(White50),
        topBar = { TopBar(navIcon = null, title = "TTECH Case", backgroundColor = GreyAppBar) }
    ) { scaffoldPadding ->
        if (state.error != null) {
            ErrorMessage(onClick = { vm.sendViewEvent(HomeContract.HomeViewEvent.GetHomeData) })
        } else if (state.isLoadingVisible) {
            CenteredCircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConnectionNotAvailableView(state.connectivityStatus)
                ConnectivityRefreshDemoDescription(refreshData = { vm.sendViewEvent(HomeContract.HomeViewEvent.GetHomeData) })
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    columns = GridCells.Fixed(columnCount)
                ) {
                    itemsIndexed(
                        items = state.products,
                        key = { index, product -> "$index${product.productId}" }) { index, product ->
                        ProductGridItem(
                            productContent = product,
                            onItemClicked = {
                                vm.sendViewEvent(
                                    HomeContract.HomeViewEvent.NavigateToDetail(
                                        navController,
                                        it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun getColumnCount(): Int {
    val phoneOrientation = LocalConfiguration.current.orientation
    return remember {
        if (phoneOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            5
        } else {
            2
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    TtechCaseTheme {
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Adaptive(128.dp)
        ) {

            itemsIndexed(
                items = arrayListOf(
                    GetListDataResult.ProductResult(
                        "12",
                        "asd",
                        3,
                        ""
                    )
                ), key = { index, product -> "$index${product.productId}" }) { index, product ->
                ProductGridItem(productContent = product)
            }
        }
    }
}

@Composable
fun ConnectionNotAvailableView(connectionStatus: ConnectionStatus) {
    if (connectionStatus == ConnectionStatus.Unavailable) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.Blue)
                .padding(vertical = 8.dp, horizontal = 32.dp)
        ) {
            TextWhite20Sp(text = stringResource(R.string.device_connection_not_available))
        }
    }
}

@Composable
fun ConnectivityRefreshDemoDescription(refreshData: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextGray16Sp(text = "Cihaz bağlantısı kapatıldığında bu liste cache'den gelmektedir. isteği yenilemek için butonu kullanınız.")
        Button(onClick = refreshData) {
            TextWhite20Sp(text = "Yenile")
        }
    }


}

@Preview
@Composable
fun ConnectionNotAvailableViewPreview() {
    ConnectionNotAvailableView(connectionStatus = ConnectionStatus.Unavailable)
}