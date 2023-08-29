package com.github.ycannot.common.composable.navigation

import com.github.ycannot.common.composable.navigation.base.FeatureApi

interface FeatureHomeApi: FeatureApi {
    companion object{
        const val PARAM_PRODUCT = "product"
    }
    fun homeRoute(): String

    fun detailRoute(): String
}