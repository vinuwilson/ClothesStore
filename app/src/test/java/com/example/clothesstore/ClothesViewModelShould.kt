package com.example.clothesstore

import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.usecase.GetProductList
import com.example.clothesstore.presenter.clothes_list.ClothesViewModel
import com.example.clothesstore.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ExampleUnitTest : BaseUnitTest() {

    private lateinit var viewModel: ClothesViewModel
    private val getProductList: GetProductList = mock()
    private val product: List<Product> = mock()
    private val expected = Result.success(product)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchAllProductList() = runTest {

        mockSuccessfulCase()

        verify(getProductList, times(1)).getAllProductList()
    }

    @Test
    fun `emit all the product list received from the product use case`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected.getOrNull(), viewModel.productListState.first().productList)
    }

    @Test
    fun `emit error when error received from product use case`() = runTest {

        mockFailureCase()

        assertEquals(null, viewModel.productListState.first().productList)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(getProductList.getAllProductList()).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = ClothesViewModel(getProductList)
    }

    private suspend fun mockFailureCase() {
        whenever(getProductList.getAllProductList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        viewModel = ClothesViewModel(getProductList)
    }
}