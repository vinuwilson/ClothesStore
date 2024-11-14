package com.example.clothesstore.presenter.clothes_list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import com.example.clothesstore.R

@Composable
fun FavouriteIcon(
    modifier: Modifier = Modifier,
    isFavourite: Boolean
) {
    IconToggleButton(
        modifier = modifier,
        checked = isFavourite,
        onCheckedChange = { }
    ) {
        if (isFavourite) {
            Icon(
                tint = Color.Red,
                modifier = modifier.graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                },
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.favourites_icon)
            )
        }
    }
}