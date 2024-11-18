package com.example.clothesstore.presenter.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.BasketEntity
import com.example.clothesstore.presenter.basket.components.BasketBottomView
import com.example.clothesstore.presenter.basket.components.BasketItem
import com.example.clothesstore.ui.theme.appColor
import com.example.clothesstore.utils.SwipeToDeleteContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketView(
    basketState: List<BasketEntity>,
    basketViewModel: BasketViewModel = hiltViewModel(),
    totalPrice: Double?
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.basket_title),
                        fontWeight = FontWeight.Bold,
                        color = appColor,
                        fontSize = dimensionResource(id = R.dimen.extra_large_font_size).value.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        if (basketState.isNotEmpty() == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(
                        items = basketState,
                        key = { "${it.name}${it.quantity}" }
                    ) { basketItem ->
                        SwipeToDeleteContainer(
                            item = basketItem,
                            onDelete = { product ->
                                basketViewModel.deleteOrUpdate(product)
                            }
                        ) {
                            BasketItem(
                                basketItem = basketItem
                            )
                        }
                    }
                }
                BasketBottomView(
                    price = totalPrice!!
                )
            }

        } else {
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.message_padding_top)),
                    textAlign = TextAlign.Center,
                    color = appColor,
                    fontStyle = FontStyle.Italic,
                    text = stringResource(R.string.empty_basket_message)
                )
            }
        }
    }
}