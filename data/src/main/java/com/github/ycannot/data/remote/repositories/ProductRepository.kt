package com.github.ycannot.data.remote.repositories

import com.github.ycannot.data.remote.services.ProductService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val service: ProductService) {

    suspend fun getListData() = service.getListData()

    suspend fun getItemDetailData(productId: String) = service.getItemDetailData(productId)

}