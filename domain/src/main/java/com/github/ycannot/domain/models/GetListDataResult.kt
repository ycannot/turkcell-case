package com.github.ycannot.domain.models


data class GetListDataResult(
    val products: List<ProductResult>
){
    data class ProductResult(
        var productId: String,
        var name: String,
        var price: Int,
        var image: String
    )

}


