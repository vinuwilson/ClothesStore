package com.example.clothesstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothesstore.presenter.navigation.Navigation
import com.example.clothesstore.ui.theme.ClothesStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClothesApp()
        }
    }
}

@Composable
fun ClothesApp() {
    ClothesStoreTheme {
        Navigation()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClothesApp()
}