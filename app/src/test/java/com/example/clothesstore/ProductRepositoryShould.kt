package com.example.clothesstore

import com.example.clothesstore.data.remote.repository.ProductRepositoryImp
import com.example.clothesstore.data.remote.repository.RemoteProductListService
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.repository.ProductRepository
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

class ProductRepositoryShould : BaseUnitTest() {
    private lateinit var productRepository: ProductRepository
    private val service: RemoteProductListService = mock()
    private val product: List<Product> = mock()
    private val expected = Result.success(product)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `fetch all the product list from service`() = runTest {

        mockSuccessfulCase()

        productRepository.getProductList()

        verify(service, times(1)).getProductList()
    }

    @Test
    fun `emit all the product list from service`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, productRepository.getProductList().first())
    }

    @Test
    fun `emit error when receive error from service`() = runTest {

        mockFailureCase()

        assertEquals(
            exception.message,
            productRepository.getProductList().first().exceptionOrNull()!!.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.getProductList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        productRepository = ProductRepositoryImp(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getProductList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        productRepository = ProductRepositoryImp(service)
    }
}