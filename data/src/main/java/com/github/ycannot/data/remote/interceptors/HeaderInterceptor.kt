package com.github.ycannot.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val updatedRequest = request.newBuilder()
            .addHeader("Build", "500")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(updatedRequest)
    }

}