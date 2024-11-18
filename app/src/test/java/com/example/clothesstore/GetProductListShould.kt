package com.example.clothesstore

import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.repository.ProductRepository
import com.example.clothesstore.domain.usecase.GetProductList
import com.example.clothesstore.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetProductListShould : BaseUnitTest() {

    private lateinit var getProduct: GetProductList
    private val repository: ProductRepository = mock()
    private val product: List<Product> = mock()
    private val expected = Result.success(product)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `fetch all the product list from repository`() = runTest {

        mockSuccessfulCase()

        getProduct.getAllProductList()

        verify(repository, times(1)).getProductList()
    }

    @Test
    fun `emit all the product list receive from repository`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, getProduct.getAllProductList().first())
    }


    @Test
    fun `emit error when receive error from repository`() = runTest {

        mockFailureCase()

        assertEquals(exception, getProduct.getAllProductList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(repository.getProductList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        getProduct = GetProductList(repository)
    }

    private suspend fun mockFailureCase() {
        whenever(repository.getProductList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        getProduct = GetProductList(repository)
    }
}