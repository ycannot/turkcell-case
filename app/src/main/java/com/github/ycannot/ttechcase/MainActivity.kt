package com.github.ycannot.ttechcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.ycannot.common.composable.navigation.FeatureHomeApi
import com.github.ycannot.common.composable.navigation.base.register
import com.github.ycannot.common.composable.theme.TtechCaseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var homeFeatureApi: FeatureHomeApi
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            TtechCaseTheme {
                // A surface container using the 'background' color from the theme
                NavHost(
                    navController = navController,
                    startDestination = homeFeatureApi.homeRoute()
                ) {
                    register(
                        homeFeatureApi,
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

