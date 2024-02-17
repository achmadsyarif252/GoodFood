package com.example.goodfood.presentation.nearby_restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.model.restaurants
import com.example.goodfood.presentation.component.CardRestaurant
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.FoodAppsTheme

@Composable
fun NearbyRestaurant(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopBar(text = "NearBy Restaurant")
        }
    ) {
        val innerPadding = it
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            columns = StaggeredGridCells.Fixed(2)
        ) {
            items(restaurants.size) { restaurantItem ->
                val restaurant = restaurants[restaurantItem]
                CardRestaurant(restaurant, modifier)
            }
        }

    }
}

@Preview
@Composable
private fun NearbyRestaurantPreview() {
    FoodAppsTheme {
        NearbyRestaurant()
    }
}

