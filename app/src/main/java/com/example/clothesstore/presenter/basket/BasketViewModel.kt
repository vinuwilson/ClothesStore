package com.example.clothesstore.presenter.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstore.domain.model.BasketEntity
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.usecase.basket.AddToBasket
import com.example.clothesstore.domain.usecase.basket.DeleteItem
import com.example.clothesstore.domain.usecase.basket.GetAllItems
import com.example.clothesstore.presenter.wish_list.WishlistState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val addToBasket: AddToBasket,
    private val deleteItem: DeleteItem,
    private val getAllItems: GetAllItems
) : ViewModel() {

    private val _basketState = MutableStateFlow(BasketState())
    val basketState = _basketState.asStateFlow()

    init {
        getAllBasketItems()
    }

    private fun getAllBasketItems() {
        viewModelScope.launch{
            _basketState.update {
                it.copy(
                    basket = getAllItems.getAllItemsFromBasket().first()
                )
            }
        }
    }

    fun addToBasket(product: BasketEntity) = viewModelScope.launch { addToBasket.addToBasket(product) }

    fun deleteItemFromBasket(product: BasketEntity) =
        viewModelScope.launch { deleteItem.deleteItemFromBasket(product) }

}