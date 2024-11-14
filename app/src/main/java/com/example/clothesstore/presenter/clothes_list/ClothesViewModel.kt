package com.example.clothesstore.presenter.clothes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.usecase.GetProductList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val getProductList: GetProductList
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState = _productListState.asStateFlow()

    init {
        getAllProductList()
    }

    private fun getAllProductList() {
        viewModelScope.launch {
            _productListState.update {
                it.copy(
                    isLoading = true
                )
            }
            _productListState.update {
                it.copy(
                    productList = getAllProduct().first().getOrNull(),
                    isLoading = false
                )
            }
        }
    }

    suspend fun getAllProduct(): Flow<Result<List<Product>>> {
        return getProductList.getAllProductList()
    }

}