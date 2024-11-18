package com.example.clothesstore.presenter.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ContentPasteSearch
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.clothesstore.R

data class BottomNavigationItem<T : Any>(
    @StringRes val title: Int,
    val route: T,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val name : String
)

val bottomBarItem = listOf(
    BottomNavigationItem(
        title = R.string.catalogue,
        route = CatalogueScreen,
        selectedIcon = Icons.Filled.ContentPasteSearch,
        unSelectedIcon = Icons.Outlined.ContentPasteSearch,
        name = "CatalogueScreen"
    ),
    BottomNavigationItem(
        title = R.string.wishlist,
        route = WishlistScreen,
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.Favorite,
        name = "WishlistScreen"
    ),
    BottomNavigationItem(
        title = R.string.basket,
        route = BasketScreen,
        selectedIcon = Icons.Filled.ShoppingCart,
        unSelectedIcon = Icons.Outlined.ShoppingCart,
        name = "BasketScreen"
    )
)