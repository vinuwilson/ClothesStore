package com.example.clothesstore.presenter.clothes_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clothesstore.R
import com.example.clothesstore.ui.theme.appColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopView(
    navController: NavHostController?,
    sheetState: SheetState?
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.app_padding))
    ) {
        Row(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.app_padding))
                .fillMaxWidth()
        ) {
            Icon(
                Icons.Filled.Close,
                tint = appColor,
                contentDescription = stringResource(R.string.model_close_icon),
                modifier = Modifier
                    .clickable {
                        scope
                            .launch {
                                sheetState?.hide()
                            }
                            .invokeOnCompletion {
                                navController?.navigateUp()
                            }
                    }
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.product_details),
                fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                color = appColor,
                fontWeight = FontWeight.Bold,
            )
        }

        HorizontalDivider(
            color = Color.LightGray,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.text_padding)),
            thickness = dimensionResource(R.dimen.divider_thickness)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DetailsTopViewPreview() {
    DetailsTopView(
        navController = null,
        sheetState = null
    )
}