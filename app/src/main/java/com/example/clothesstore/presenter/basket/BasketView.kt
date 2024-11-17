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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.clothesstore.R
import com.example.clothesstore.presenter.basket.components.BasketBottomView
import com.example.clothesstore.presenter.basket.components.BasketItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketView(
    basketState: BasketState,
    basketViewModel: BasketViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.basket_title),
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = dimensionResource(id = R.dimen.extra_large_font_size).value.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        if (basketState.basket?.isNotEmpty() == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(basketState.basket) { basketItem ->
                        BasketItem(
                            basketItem = basketItem
                        )
                    }
                }
                BasketBottomView(
                    price = 10.00
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
                    color = Color.Red,
                    fontStyle = FontStyle.Italic,
                    text = stringResource(R.string.empty_basket_message)
                )
            }
        }
    }
}