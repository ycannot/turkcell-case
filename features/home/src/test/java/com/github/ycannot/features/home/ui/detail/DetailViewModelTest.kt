package com.github.ycannot.features.home.ui.detail

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.github.ycannot.domain.usecases.GetItemDetailUseCase
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.reflect.Method

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = HiltTestApplication::class,manifest=Config.NONE)
class DetailViewModelTest {
    private lateinit var getItemDetailUseCase: GetItemDetailUseCase
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var isNotProductIdValidMethod: Method
    private lateinit var redirectMethod: Method

    @Before
    fun setUp() {
        getItemDetailUseCase = mockk()
        detailViewModel = DetailViewModel(getItemDetailUseCase)
        isNotProductIdValidMethod = detailViewModel.javaClass.getDeclaredMethod("isNotProductIdValid", String::class.java)
        isNotProductIdValidMethod.isAccessible = true
        redirectMethod = detailViewModel.javaClass.getDeclaredMethod("redirect", Context::class.java)
        redirectMethod.isAccessible = true
    }

    @Test
    fun `isNotProductIdValid test with empty string`(){
        val parameters = arrayOf<Any?>("")
        assertEquals(true, isNotProductIdValidMethod.invoke(detailViewModel, *parameters) )
    }

    @Test
    fun `isNotProductIdValid test with null`(){
        val parameters = arrayOf<Any?>(null)

        assertEquals(true, isNotProductIdValidMethod.invoke(detailViewModel, *parameters) )
    }

    @Test
    fun `isNotProductIdValid test with valid numeric string`(){
        val parameters = arrayOf<Any?>("2")

        assertEquals(false, isNotProductIdValidMethod.invoke(detailViewModel, *parameters) )
    }

    @Test
    fun `isNotProductIdValid test with valid alphanumeric string`(){
        val parameters = arrayOf<Any?>("string_product_id_2")

        assertEquals(false, isNotProductIdValidMethod.invoke(detailViewModel, *parameters) )
    }

    @Test
    fun `redirect test`(){
        val parameters = arrayOf<Any>(ApplicationProvider.getApplicationContext())
        assertEquals(true, redirectMethod.invoke(detailViewModel, *parameters) )
    }
}