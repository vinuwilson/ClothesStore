package com.example.clothesstore.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import  com.example.clothesstore.R

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    context: Context,
    imageUri: String?,
    placeholder: Int = R.drawable.placeholder_image
) {
    if (imageUri.isNullOrEmpty()) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_image),
            contentDescription = "Product image",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

    } else {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUri)
                .crossfade(true)
                .placeholder(placeholder)
                .build(),
            contentDescription = "Product image",
            error = painterResource(id = R.drawable.placeholder_image),
            contentScale = ContentScale.Inside,
            modifier = modifier.padding(12.dp)
                .clip(CircleShape)
        )
    }
}