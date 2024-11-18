package com.example.clothesstore.presenter.clothes_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.clothesstore.R
import com.example.clothesstore.presenter.clothes_details.components.DetailsBottomView
import com.example.clothesstore.presenter.clothes_details.components.DetailsInfoView
import com.example.clothesstore.presenter.clothes_details.components.DetailsTopView
import com.example.clothesstore.presenter.clothes_list.ProductListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesDetails(
    navController: NavHostController,
    productId: String,
    productDetails: ProductListState
) {
    val productDetails = productDetails.productList?.find { it.productId == productId }
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        containerColor = Color.Transparent,
        dragHandle = { BottomSheetDefaults.DragHandle(height = 0.dp, width = 0.dp) },
        onDismissRequest = { navController.navigateUp() }
    ) {
        if (productDetails != null) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.onPrimary),
            ) {
                DetailsTopView(
                    navController = navController,
                    sheetState = modalBottomSheetState
                )

                Row(
                    modifier = Modifier.weight(1F)
                ) {
                    DetailsInfoView(
                        productDetails = productDetails
                    )
                }
                DetailsBottomView(
                    productDetails = productDetails
                )
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                Text(text = stringResource(R.string.details_not_found))
            }
        }
    }
}