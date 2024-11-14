package com.example.clothesstore

import com.example.clothesstore.data.remote.api.ProductApi
import com.example.clothesstore.data.remote.dto.ProductListDto
import com.example.clothesstore.data.remote.repository.RemoteProductListService
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.model.ProductMapper
import com.example.clothesstore.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RemoteProductListServiceShould : BaseUnitTest() {

    private lateinit var productListService: RemoteProductListService
    private val mapper: ProductMapper = mock()
    private val api: ProductApi = mock()
    private val productDto: ProductListDto = mock()
    private val product: List<Product> = mock()
    private val expected = Result.success(product)
    private val exception = "Something went wrong"

    @Test
    fun `fetch product list from the server`() = runTest {

        mockSuccessfulCase()

        api.getProductList()

        verify(api, times(1)).getProductList()
    }

    @Test
    fun `convert product list to flow and emit`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, productListService.getProductList().first())
    }

    @Test
    fun `emit error when network fails`() = runTest {

        mockFailureCase()

        assertEquals(exception, productListService.getProductList().first().exceptionOrNull()!!.message)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(api.getProductList()).thenReturn(productDto)

        whenever(mapper.invoke(productDto.products)).thenReturn(product)

        productListService = RemoteProductListService(api, mapper)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getProductList()).thenThrow(RuntimeException("Damn backend exception"))

        productListService = RemoteProductListService(api, mapper)
    }
}