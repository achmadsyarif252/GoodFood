package com.example.goodfood.presentation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.goodfood.R
import com.example.goodfood.domain.model.BottomBarItem
import com.exyte.animatednavbar.AnimatedNavigationBar


@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {

    NavigationBar {
        val bottomNavigation = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.txt_search),
                icon = Icons.Default.Search
            ),
            BottomBarItem(
                title = stringResource(id = R.string.favorite),
                icon = Icons.Default.FavoriteBorder
            ),
            BottomBarItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home
            ), BottomBarItem(
                title = stringResource(id = R.string.profile),
                icon = Icons.Default.ShoppingCart
            ),
            BottomBarItem(
                title = stringResource(id = R.string.person),
                icon = Icons.Default.Person
            )
        )

        bottomNavigation.map {
            NavigationBarItem(
                selected = it.title == bottomNavigation[0].title,
                onClick = { },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
            )
        }
    }
}
