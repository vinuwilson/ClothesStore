package com.example.clothesstore.presenter.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.clothesstore.presenter.basket.BasketView
import com.example.clothesstore.presenter.basket.BasketViewModel
import com.example.clothesstore.presenter.clothes_details.ClothesDetails
import com.example.clothesstore.presenter.clothes_list.ClothesList
import com.example.clothesstore.presenter.clothes_list.ClothesViewModel
import com.example.clothesstore.presenter.wish_list.WishListView
import com.example.clothesstore.presenter.wish_list.WishListViewModel
import com.example.clothesstore.ui.theme.appColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val badgeViewModel: BottomBarViewModel = hiltViewModel()
    val badgeCounts = badgeViewModel.badgeCounts.collectAsStateWithLifecycle().value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomBarItem.forEach { screen ->
                    val badgeCount = badgeCounts[screen.name] ?: 0
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.hasRoute(screen.route::class) } == true
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = appColor,
                        ),
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (badgeCount > 0) {
                                        Badge {
                                            Text(text = badgeCount.toString())
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (isSelected) {
                                        screen.selectedIcon
                                    } else {
                                        screen.unSelectedIcon
                                    },
                                    contentDescription = stringResource(screen.title)
                                )
                            }

                        },
                        label = { Text(stringResource(screen.title)) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ClothesInfo,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            navigation<ClothesInfo>(
                startDestination = CatalogueScreen
            ) {
                composable<CatalogueScreen> { entry ->
                    val viewmodel =
                        entry.sharedViewModel<ClothesViewModel>(navController = navController)
                    val productState by viewmodel.productListState.collectAsStateWithLifecycle()

                    ClothesList(
                        navController = navController,
                        productList = productState
                    )
                }

                composable<ClothesDetailsScreen> { entry ->
                    val viewmodel =
                        entry.sharedViewModel<ClothesViewModel>(navController = navController)
                    val productState by viewmodel.productListState.collectAsStateWithLifecycle()
                    val args = entry.toRoute<ClothesDetailsScreen>()
                    ClothesDetails(
                        navController = navController,
                        productId = args.productId,
                        productDetails = productState
                    )
                }
            }

            composable<WishlistScreen> {
                val viewModel: WishListViewModel = hiltViewModel()
                val wishlistState = viewModel.wishlistState.collectAsStateWithLifecycle().value
                WishListView(
                    wishlistState = wishlistState,
                    wishListViewModel = viewModel
                )
            }

            composable<BasketScreen> {
                val viewModel: BasketViewModel = hiltViewModel()
                val basketState = viewModel.basketState.collectAsStateWithLifecycle().value
                val totalPrice by viewModel.total.collectAsStateWithLifecycle()
                BasketView(
                    basketState = basketState,
                    basketViewModel = viewModel,
                    totalPrice = totalPrice
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}