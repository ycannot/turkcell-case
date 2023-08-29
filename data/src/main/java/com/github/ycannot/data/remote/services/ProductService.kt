package com.github.ycannot.data.remote.services

import com.github.ycannot.data.models.response.GetItemDetailResponse
import com.github.ycannot.data.models.response.GetListResponse
import com.github.ycannot.data.remote.MockResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductService {

    @MockResponse("mocks/get_item_detail_response.json")
    @GET(GET_ITEM_DETAIL_ENDPOINT)
    suspend fun getItemDetailData(@Path(PRODUCT_ID_KEY) productId: String): GetItemDetailResponse


    @MockResponse("mocks/get_list_response.json")
    @GET(GET_LIST_ENDPOINT)
    suspend fun getListData(): GetListResponse


    companion object {
        private const val PRODUCT_ID_KEY = "productId"
        private const val GET_ITEM_DETAIL_ENDPOINT =
            "/developer-application-test/cart/{$PRODUCT_ID_KEY}/detail"
        private const val GET_LIST_ENDPOINT =
            "/developer-application-test/cart/list"

    }
}