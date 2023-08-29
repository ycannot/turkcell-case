package com.github.ycannot.domain.mappers

import com.github.ycannot.core.extensions.orZero
import com.github.ycannot.data.models.response.GetListResponse
import com.github.ycannot.domain.models.GetListDataResult


fun GetListResponse?.toDomainModel() = GetListDataResult(
    this?.products?.map { GetListDataResult.ProductResult(
        productId = it.productId.orEmpty(),
        name = it.name.orEmpty(),
        price = it.price.orZero(),
        image = it.image.orEmpty()
    ) } ?: arrayListOf()
)