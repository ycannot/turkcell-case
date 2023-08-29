package com.github.ycannot.data.remote.interceptors

import com.github.ycannot.data.BuildConfig
import com.github.ycannot.data.remote.MockResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation
import javax.net.ssl.HttpsURLConnection

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!BuildConfig.IS_MOCK) {
            return chain.proceed(chain.request())
        }

        val request = chain.request()
        val mockResponseStr = kotlin.runCatching { request.getMockResponse() }.getOrNull()
        val responseCode: Int = mockResponseStr?.let {
            HttpsURLConnection.HTTP_OK
        } ?: HttpsURLConnection.HTTP_INTERNAL_ERROR
        return Response
            .Builder()
            .request(request)
            .code(responseCode)
            .protocol(Protocol.HTTP_1_1)
            .message(mockResponseStr ?: "")
            .body(
                mockResponseStr
                    ?.toByteArray()
                    ?.toResponseBody(
                        "application/json".toMediaTypeOrNull()
                    )
            )
            .addHeader("content-is-mock", "true")
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun Request.getMockResponse() =
        this.tag(Invocation::class.java)
            ?.method()
            ?.getAnnotation(MockResponse::class.java)
            .readJson()

    private fun MockResponse?.readJson() =
        javaClass.classLoader?.getResourceAsStream(this?.path)
            ?.bufferedReader()
            .use { it?.readText() }

}