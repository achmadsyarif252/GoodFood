package com.example.goodfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.goodfood.presentation.home.HomeScreen
import com.example.goodfood.ui.theme.FoodAppsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppsTheme {
                HomeScreen()
            }
        }
    }
}


