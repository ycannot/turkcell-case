package com.github.ycannot.features.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.ycannot.common.composable.navigation.FeatureHomeApi
import com.github.ycannot.common.composable.navigation.FeatureHomeApi.Companion.PARAM_PRODUCT
import com.github.ycannot.core.extensions.cast
import com.github.ycannot.domain.models.GetListDataResult
import com.github.ycannot.features.home.ui.detail.DetailScreen
import com.github.ycannot.features.home.ui.home.HomeScreen

class FeatureHomeImpl : FeatureHomeApi {
    private val homePath = "homeRoute"
    private val detailPath = "detailRoute"

    override fun homeRoute(): String = homePath

    override fun detailRoute(): String = "$detailPath"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = homeRoute()) {
            HomeScreen(modifier = modifier, navController = navController)
        }

        navGraphBuilder.composable(
            route = "$detailPath"
        ) {
            val product = navController.previousBackStackEntry
                ?.savedStateHandle?.get<String>(PARAM_PRODUCT).cast<GetListDataResult.ProductResult>()
            DetailScreen(
                modifier = modifier,
                navController = navController,
                product = product
            )
        }
    }
}