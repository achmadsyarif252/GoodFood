package com.example.goodfood.presentation.bottombar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goodfood.core.domain.model.bottomNavigation
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.ui.theme.Gold


@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    NavigationBar {
        bottomNavigation.map {
            NavigationBarItem(
                selected = it.title == bottomNavigation[2].title,
                onClick = {
                    when (it) {
                        bottomNavigation[0] -> {
                            navController.navigate("nearbyResto")
                        }

                        bottomNavigation[1] -> {
                            navController.navigate("favorite")
                        }

                        bottomNavigation[3] -> {
                            navController.navigate("cart")
                        }

                        bottomNavigation[4] -> {
//                            navController.navigate("review")
                            navController.navigate("profile")
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title.toString(),
                        tint = Gold,
                        modifier = Modifier.size(28.dp)
                    )
                },
            )
        }
    }
}

