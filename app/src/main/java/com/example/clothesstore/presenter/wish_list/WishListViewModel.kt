package com.example.clothesstore.presenter.wish_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.usecase.wishlist.AddToFavourite
import com.example.clothesstore.domain.usecase.wishlist.DeleteFavourite
import com.example.clothesstore.domain.usecase.wishlist.GetFavourites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val addToFavourite: AddToFavourite,
    private val getFavourites: GetFavourites,
    private val deleteFavourite: DeleteFavourite
) : ViewModel() {

    private val _wishlistState = MutableStateFlow(WishlistState())
    val wishlistState = _wishlistState.asStateFlow()

    init {
        getAllWishlist()
    }

    fun getAllWishlist() {
        viewModelScope.launch(Dispatchers.IO) {
            _wishlistState.update {
                it.copy(
                    wishlist = getFavourites.getFavourites().first()
                )
            }
        }
    }

    fun addToFavourite(product: Product) =
        viewModelScope.launch { addToFavourite.addToFavourite(product) }

    fun removeFromFavourite(product: Product) =
        viewModelScope.launch { deleteFavourite.removeFromFavourite(product) }

}