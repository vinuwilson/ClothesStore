package com.example.clothesstore.presenter.clothes_details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.clothesstore.R

@Composable
fun DetailsCells(
    title: String,
    details: String
) {
    Text(
        text = title,
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.text_padding)),
        fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp,
        color =  MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Medium,
    )

    Text(
        text = details,
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.text_padding)),
        fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp,
        color = Color.Gray
    )

    HorizontalDivider(
        color = Color.LightGray,
        thickness = dimensionResource(R.dimen.divider_thickness),
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.text_padding))
    )
}