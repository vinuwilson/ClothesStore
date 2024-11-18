package com.example.clothesstore.presenter.wish_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.ui.theme.appColor
import com.example.clothesstore.utils.CoilImage

@Composable
fun WishlistItem(
    wishlistItem: Product,
    onItemMoveToCart: (Product) -> Unit
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
                    imageUri = wishlistItem.image,
                    modifier = Modifier
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.app_padding))
                    .weight(.8F),
            ) {
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = dimensionResource(R.dimen.divider_thickness),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.text_padding))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        color =  MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.text_padding_small)),
                        text = wishlistItem.name,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        modifier = Modifier.clickable {
                            onItemMoveToCart(wishlistItem)
                        },
                        tint = appColor,
                        imageVector = Icons.Default.ShoppingCartCheckout,
                        contentDescription = stringResource(R.string.basket_ShoppingCart)
                    )
                }

                Text(
                    color = appColor,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.text_padding)),
                    text = "$${wishlistItem.price}",
                    fontWeight = FontWeight.ExtraBold
                )

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
fun WishlistItemPreview() {
    WishlistItem(
        wishlistItem = Product(
            category = "Tops",
            name = "Blue Shirt",
            image = "https://firebasestorage.googleapis.com/v0/b/techtest-1f1da.appspot.com/o/blue.jpg?alt=media\u0026token=5be5eadd-c006-4bb9-83af-d6afc496475a",
            price = 7.99,
            stock = 3,
            oldPrice = 8.99,
            productId = "1"
        ),
        onItemMoveToCart = {}
    )
}