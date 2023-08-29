package com.github.ycannot.domain.mappers

import com.github.ycannot.core.extensions.orZero
import com.github.ycannot.data.models.response.GetItemDetailResponse
import com.github.ycannot.domain.models.GetItemDetailResult

fun GetItemDetailResponse?.toDomainModel() = GetItemDetailResult(
    productId = this?.productId.orEmpty(),
    name = this?.name.orEmpty(),
    price = this?.price.orZero(),
    image = this?.image.orEmpty(),
    description = this?.description.orEmpty()
)