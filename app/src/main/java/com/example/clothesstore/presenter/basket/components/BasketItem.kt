package com.example.clothesstore.presenter.basket.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.BasketEntity
import com.example.clothesstore.utils.CoilImage

@Composable
fun BasketItem(
    basketItem: BasketEntity
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(.2F)
            ) {
                CoilImage(
                    context = context,
                    imageUri = basketItem.image,
                    modifier = Modifier
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.app_padding))
                    .weight(.8F),
            ) {
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = dimensionResource(R.dimen.divider_thickness),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.text_padding))
                )

                Text(
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.text_padding_small)),
                    text = basketItem.name,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        color = Color.Red,
                        modifier = Modifier
                            .weight(.5F)
                            .padding(top = dimensionResource(R.dimen.text_padding_small)),
                        text = "$${basketItem.price}",
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        color = Color.Red,
                        modifier = Modifier
                            .weight(.5F)
                            .padding(top = dimensionResource(R.dimen.text_padding_small)),
                        text = "${stringResource(R.string.quantity)}: ${basketItem.quantity}",
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = dimensionResource(R.dimen.divider_thickness),
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.text_padding))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasketItemPreview() {
    BasketItem(
        basketItem = BasketEntity(
            category = "Tops",
            name = "Blue Shirt",
            image = "https://firebasestorage.googleapis.com/v0/b/techtest-1f1da.appspot.com/o/blue.jpg?alt=media\u0026token=5be5eadd-c006-4bb9-83af-d6afc496475a",
            price = 7.99,
            stock = 3,
            oldPrice = 8.99,
            productId = "1",
            quantity = 1
        )
    )
}