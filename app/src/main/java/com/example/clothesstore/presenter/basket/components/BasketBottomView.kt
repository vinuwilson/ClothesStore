package com.example.clothesstore.presenter.basket.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.clothesstore.R

@Composable
fun BasketBottomView(
    price: Double = 10.00
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.app_padding))
                .weight(1f),
            onClick = {}
        ) {
            Text(
                text = stringResource(R.string.check_out),
                modifier = Modifier.padding(dimensionResource(R.dimen.button_text_padding))
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$${price}",
                fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.ExtraBold,
            )

            Text(
                text = stringResource(R.string.total),
                fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasketBottomViewPreview() {
    BasketBottomView(
        price = 7.99
    )
}