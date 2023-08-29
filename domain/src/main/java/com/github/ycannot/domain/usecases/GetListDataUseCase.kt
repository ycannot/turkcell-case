package com.github.ycannot.domain.usecases

import com.github.ycannot.data.local.TtechCacheManager
import com.github.ycannot.data.remote.repositories.ProductRepository
import com.github.ycannot.domain.mappers.toDomainModel
import com.github.ycannot.domain.models.GetListDataResult
import com.github.ycannot.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class GetListDataUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val ttechCacheManager: TtechCacheManager
) : BaseUseCase<GetListDataUseCase.Params, GetListDataResult> {

    data class Params(val isOffline: Boolean = false)

    override suspend fun run(params: Params): GetListDataResult {
        return if (params.isOffline) {
            ttechCacheManager.getListResponse.toDomainModel()
        } else {
            val result = productRepository.getListData()
            ttechCacheManager.getListResponse = result
            result.toDomainModel()
        }
    }

}