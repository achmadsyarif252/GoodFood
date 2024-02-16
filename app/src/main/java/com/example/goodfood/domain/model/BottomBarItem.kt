package com.example.goodfood.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.goodfood.R

data class BottomBarItem(val title: Int, val icon: ImageVector)

val bottomNavigation = listOf(
    BottomBarItem(
        title = R.string.txt_search,
        icon = Icons.Default.Search,

        ),
    BottomBarItem(
        title = R.string.favorite,
        icon = Icons.Default.FavoriteBorder
    ),
    BottomBarItem(
        title = R.string.home,
        icon = Icons.Default.Home
    ), BottomBarItem(
        title = R.string.profile,
        icon = Icons.Default.ShoppingCart,
    ),
    BottomBarItem(
        title = R.string.person,
        icon = Icons.Default.Person
    )
)