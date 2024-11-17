package com.example.clothesstore.presenter.clothes_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.utils.CoilImage

@Composable
fun DetailsInfoView(
    productDetails: Product
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.app_padding))
    ) {

        Row(
            Modifier.height((LocalConfiguration.current.screenHeightDp / 3F).dp)
        ) {
            Box(
                Modifier.weight(1f)
            ) {
                CoilImage(
                    context = context,
                    imageUri = productDetails.image,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Box (
                modifier = Modifier.fillMaxHeight()
            ) {
                Column {
                    Text(
                        text = "$${productDetails.price}",
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.text_padding))
                            .align(Alignment.End),
                        fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.ExtraBold,
                    )

                    Text(
                        text = if (productDetails.oldPrice > 0)
                            "$${productDetails.oldPrice}"
                        else
                            "",
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.text_padding))
                            .align(Alignment.End),
                        fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                        color = Color.LightGray,
                        style = LocalTextStyle.current.copy(
                            color = Color.Red
                        ),
                        textDecoration = TextDecoration.LineThrough,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }


                Text(
                    text = if (productDetails.stock >= 1)
                        stringResource(R.string.in_stock)
                    else
                        stringResource(R.string.out_of_stock),
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.text_padding))
                        .align(Alignment.BottomEnd),
                    fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        HorizontalDivider(
            color = Color.LightGray,
            thickness = dimensionResource(R.dimen.divider_thickness),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.text_padding))
        )

        DetailsCells(
            title = stringResource(R.string.name),
            details = productDetails.name
        )

        DetailsCells(
            title = stringResource(R.string.category),
            details = productDetails.category
        )

        DetailsCells(
            title = stringResource(R.string.amount_in_stock),
            details = productDetails.stock.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsInfoViewPreview() {
    DetailsInfoView(
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