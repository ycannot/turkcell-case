package com.github.ycannot.domain.usecases

import android.os.Build
import com.github.ycannot.core.extensions.cast
import com.github.ycannot.core.extensions.serialize
import com.github.ycannot.data.models.response.GetListResponse
import com.github.ycannot.data.remote.repositories.ProductRepository
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = HiltTestApplication::class,manifest= Config.NONE)
class GetListDataUseCaseTest {


    private lateinit var getListDataUseCase: GetListDataUseCase
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productRepository = mockk(relaxed = true)
        coEvery { productRepository.getListData() } returns response.cast<GetListResponse>()!!
        getListDataUseCase = GetListDataUseCase(productRepository)

    }

    @Test
    fun `GetListDataUseCase invoke & mapping test 0`() = runTest {
        val getListResult =
            getListDataUseCase(GetListDataUseCase.Params())
        println(getListResult.serialize())
        assertEquals (true, getListResult.products.isNotEmpty())
    }

    @Test
    fun `GetListDataUseCase invoke & mapping test 1`() = runTest {
        val getListResult =
            getListDataUseCase(GetListDataUseCase.Params())
        println(getListResult.serialize())
        assertEquals (true, getListResult.products.first().name.isNotEmpty())
    }

    companion object{
        private const val response = "{\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "            \"product_id\": \"1\",\n" +
                "            \"name\": \"Apples\",\n" +
                "            \"price\": 120,\n" +
                "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"product_id\": \"2\",\n" +
                "            \"name\": \"Oranges\",\n" +
                "            \"price\": 167,\n" +
                "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/2.jpg\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"
    }

}