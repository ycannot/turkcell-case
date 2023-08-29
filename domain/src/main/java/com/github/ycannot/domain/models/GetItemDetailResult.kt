package com.github.ycannot.domain.models

data class GetItemDetailResult(
    var productId: String,
    var name: String,
    var price: Int,
    var image: String,
    var description: String
)


