package com.github.ycannot.domain.usecases

import android.os.Build
import com.github.ycannot.core.extensions.cast
import com.github.ycannot.core.extensions.serialize
import com.github.ycannot.data.models.response.GetItemDetailResponse
import com.github.ycannot.data.remote.repositories.ProductRepository
import com.github.ycannot.data.remote.services.ProductService
import com.github.ycannot.domain.models.GetItemDetailResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = HiltTestApplication::class,manifest= Config.NONE)
class GetItemDetailUseCaseTest {


    private lateinit var getItemDetailUseCase: GetItemDetailUseCase
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productRepository = mockk(relaxed = true)
        coEvery { productRepository.getItemDetailData(any()) } returns response.cast<GetItemDetailResponse>()!!
        getItemDetailUseCase = GetItemDetailUseCase(productRepository)

    }

    @Test
    fun `GetItemDetailUseCase invoke & mapping test`() = runTest {
        val getItemDetailResult =
            getItemDetailUseCase.invoke(GetItemDetailUseCase.Params("1"))
        println(getItemDetailResult.serialize())
        assertEquals (true, getItemDetailResult.name.isNotEmpty())
    }

    companion object{
        private const val response = "{\n" +
                "    \"product_id\": \"1\",\n" +
                "    \"name\": \"Apples\",\n" +
                "    \"price\": 120,\n" +
                "    \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg\",\n" +
                "    \"description\": \"An apple a day keeps the doctor away.\"\n" +
                "}"
    }

}