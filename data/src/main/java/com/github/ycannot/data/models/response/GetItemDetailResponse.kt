package com.github.ycannot.data.models.response

import com.google.gson.annotations.SerializedName

data class GetItemDetailResponse(
    @SerializedName("product_id") var productId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("description") var description: String? = null
)