package com.example.goodfood.presentation.review

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.goodfood.presentation.component.TopBar

@Composable
fun ReviewScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopBar(text = "Review")
        }
    ) {
        val innerPadding = it
        Column {

        }
    }
}