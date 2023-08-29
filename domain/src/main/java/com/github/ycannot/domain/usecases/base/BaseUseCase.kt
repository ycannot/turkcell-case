package com.github.ycannot.domain.usecases.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BaseUseCase<P, R> {
    suspend operator fun invoke(params: P, dispatcher: CoroutineDispatcher = Dispatchers.IO): R = withContext(dispatcher) { run(params) }

    suspend fun run(params: P): R
}