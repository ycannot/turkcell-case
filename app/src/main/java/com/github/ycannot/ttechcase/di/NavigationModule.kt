package com.github.ycannot.ttechcase.di

import com.github.ycannot.common.composable.navigation.FeatureHomeApi
import com.github.ycannot.features.home.navigation.FeatureHomeImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigationModule {

    @Provides
    @Singleton
    fun providePreferenceManager(): FeatureHomeApi = FeatureHomeImpl()

}