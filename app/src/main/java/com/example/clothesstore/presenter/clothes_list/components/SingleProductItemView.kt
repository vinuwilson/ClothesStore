package com.example.clothesstore.presenter.clothes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.utils.CoilImage

@Composable
fun SingleProductItemView(
    product: Product,
    onItemClicked: (String) -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.card_view_height))
            .padding(dimensionResource(R.dimen.card_view_padding))
            .clickable { onItemClicked(product.productId) },
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.card_view_elevation)),
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CoilImage(
                    context = context,
                    imageUri = product.image
                )

                Text(
                    text = product.name,
                    modifier = Modifier.padding( start = dimensionResource(id = R.dimen.text_padding)),
                    fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp,
                    color = Color.Gray
                )

                Text(
                    text = "$${product.price}",
                    modifier = Modifier.padding( dimensionResource(id = R.dimen.text_padding)),
                    fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
            FavouriteIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(dimensionResource(R.dimen.favourite_padding)),
                isFavourite = product.isFavourite,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleProductItemViewPreview() {
    SingleProductItemView(
        product = Product(
            category = "Tops",
            name = "Blue Shirt",
            image = "https://firebasestorage.googleapis.com/v0/b/techtest-1f1da.appspot.com/o/blue.jpg?alt=media\u0026token=5be5eadd-c006-4bb9-83af-d6afc496475a",
            price = 7.99,
            stock = 3,
            oldPrice = 8.99,
            productId = "1"
        ),
        onItemClicked = {}
    )
}