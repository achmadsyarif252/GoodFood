package com.example.goodfood.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.presentation.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDefault(
    modifier: Modifier = Modifier,
    text: String,
    isProfileScreen: Boolean = false,
    onExit: () -> Unit = {}
) {
    val controller = LocalNavController.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { controller.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            if (isProfileScreen) IconButton(onClick = { onExit() }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Quit App")
            }
        },
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = text,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset((-8).dp)
                )
            }
        })
}