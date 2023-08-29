package com.github.ycannot.data.di

import com.github.ycannot.core.managers.interfaces.PreferenceManager
import com.github.ycannot.data.BuildConfig
import com.github.ycannot.data.constants.DataConstants
import com.github.ycannot.data.local.TtechCacheManager
import com.github.ycannot.data.local.TtechCacheManagerImpl
import com.github.ycannot.data.remote.interceptors.EscapeInterceptor
import com.github.ycannot.data.remote.interceptors.HeaderInterceptor
import com.github.ycannot.data.remote.interceptors.MockInterceptor
import com.github.ycannot.data.remote.services.ProductService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideMockResponseInterceptor() = MockInterceptor()

    @Provides
    @Singleton
    fun provideHeaderInterceptor() = HeaderInterceptor()

    @Provides
    @Singleton
    fun provideEscapeInterceptor() = EscapeInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.BUILD_TYPE != "release") {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        mockInterceptor: MockInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        escapeInterceptor: EscapeInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        //builder.addInterceptor(headerInterceptor)
        if (BuildConfig.IS_MOCK) {
            builder.addInterceptor(mockInterceptor)
        }
        builder.addInterceptor(loggingInterceptor)
        builder.addInterceptor(escapeInterceptor)

        return builder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DataConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }


    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Provides
    fun provideTtechCacheManager(preferenceManager: PreferenceManager): TtechCacheManager =
        TtechCacheManagerImpl(preferenceManager)

}