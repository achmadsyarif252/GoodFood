package com.example.goodfood.presentation.favorite

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.LocalNavController
import com.example.goodfood.ui.theme.Gold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val controller = LocalNavController.current
    var isFoodSelected by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { controller.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Favorite",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset((-8).dp)
                        )
                    }
                })
        }
    ) {
        val padding = it
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Row {
                OutlinedButton(
                    border = BorderStroke(
                        1.dp, brush = Brush.linearGradient(
                            colors = listOf(
                                Gold,
                                Gold,
                            )
                        )
                    ),
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFoodSelected) Gold else Color.White,
                        contentColor = if (isFoodSelected) Color.White else Gold,
                    ),
                    onClick = { isFoodSelected = true }, shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Food")
                }
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedButton(
                    border = BorderStroke(
                        1.dp, brush = Brush.linearGradient(
                            colors = listOf(
                                Gold,
                                Gold
                            )
                        )
                    ),
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFoodSelected) Color.White else Gold,
                        contentColor = if (isFoodSelected) Gold else Color.White,
                    ),
                    onClick = { isFoodSelected = false }, shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Restaurant")
                }
            }
        }
    }
}