package com.example.clothesstore.presenter.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstore.domain.usecase.basket.GetItems
import com.example.clothesstore.domain.usecase.wishlist.GetFavourites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor(
    private val getFavourites: GetFavourites,
    private val getItems: GetItems
) : ViewModel() {

    private val _badgeCounts = MutableStateFlow(mapOf<String, Int>())
    val badgeCounts = _badgeCounts
        .onStart { wishlistBasketCount() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyMap()
        )

    init {
        wishlistBasketCount()
    }

    fun wishlistBasketCount(
    ) {
        viewModelScope.launch {
            val wishlistCount = getFavourites.getFavourites()
            val basketCount = getItems.getBasketCount()
            wishlistCount.combine(basketCount) { a, b ->
                mapOf(
                    "WishlistScreen" to a.size,
                    "BasketScreen" to b
                )
            }.collectLatest {
                _badgeCounts.value = it
            }
        }
    }
}