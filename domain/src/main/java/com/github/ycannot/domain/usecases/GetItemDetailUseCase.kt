package com.github.ycannot.domain.usecases

import com.github.ycannot.data.remote.repositories.ProductRepository
import com.github.ycannot.domain.mappers.toDomainModel
import com.github.ycannot.domain.models.GetItemDetailResult
import com.github.ycannot.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class GetItemDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<GetItemDetailUseCase.Params, GetItemDetailResult> {

    data class Params(val productId: String)

    override suspend fun run(params: Params): GetItemDetailResult {
        return productRepository.getItemDetailData(params.productId).toDomainModel()
    }

}