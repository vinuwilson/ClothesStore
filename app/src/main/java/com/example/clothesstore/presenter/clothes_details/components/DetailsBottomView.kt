package com.example.clothesstore.presenter.clothes_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.Product

@Composable
fun DetailsBottomView(
    productDetails: Product
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.app_padding))
                .weight(1f),
            onClick = {}
        ) {
            Text(
                text = stringResource(R.string.wishlist),
                color = Color.DarkGray,
                modifier = Modifier.padding(dimensionResource(R.dimen.button_text_padding))
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.app_padding))
                .weight(1f),
            onClick = {}
        ) {
            Text(
                modifier = Modifier.padding(dimensionResource(R.dimen.button_text_padding)),
                text = stringResource(R.string.add_to_cart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsBottomViewPreview() {
    DetailsBottomView(
        productDetails = Product(
            category = "Tops",
            name = "Blue Shirt",
            image = "https://firebasestorage.googleapis.com/v0/b/techtest-1f1da.appspot.com/o/blue.jpg?alt=media\u0026token=5be5eadd-c006-4bb9-83af-d6afc496475a",
            price = 7.99,
            stock = 3,
            oldPrice = 8.99,
            productId = "1"
        )
    )
}