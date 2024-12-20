package com.example.clothesstore.presenter.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstore.domain.model.BasketEntity
import com.example.clothesstore.domain.usecase.basket.AddToBasket
import com.example.clothesstore.domain.usecase.basket.DeleteItem
import com.example.clothesstore.domain.usecase.basket.GetItems
import com.example.clothesstore.domain.usecase.basket.GetTotalAmount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val addToBasket: AddToBasket,
    private val deleteItem: DeleteItem,
    private val getItems: GetItems,
    private val getTotalAmount: GetTotalAmount
) : ViewModel() {

    private val _basketState = MutableStateFlow<List<BasketEntity>>(emptyList())
    val basketState = _basketState.asStateFlow()

    private val _total = MutableStateFlow(0.0)
    val total = _total
        .onStart { getTotal() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    init {
        getAllBasketItems()
    }

    private fun getAllBasketItems() {
        viewModelScope.launch {
            getItems.getAllItemsFromBasket().collectLatest {
                _basketState.value = it
            }
        }
    }

    private fun getTotal() {
        viewModelScope.launch {
            getTotalAmount.getTotal().collectLatest {
                _total.value = it
            }
        }
    }

    fun insertOrUpdate(entity: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val itemsFromDb = getItems.getItemById(entity.productId)
            if(itemsFromDb.isEmpty())
                addToBasket.addToBasket(entity)
            else
                addToBasket.addQuantity(entity.productId)
        }
    }

    fun deleteOrUpdate(entity: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            if(entity.quantity == 1)
                deleteItem.deleteItemFromBasket(entity)
            else
                deleteItem.deleteSingleItemFromBasket(entity.productId)
        }
    }
}