package com.example.goodfood.presentation.nearby_restaurant


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goodfood.presentation.FoodViewModelFactory
import com.example.goodfood.presentation.component.CardRestaurant
import com.example.goodfood.presentation.component.TopBarDefault
import com.example.goodfood.ui.theme.FoodAppsTheme

@Composable
fun NearbyRestaurant(
    modifier: Modifier = Modifier,
) {
    val restaurantViewModel: RestaurantViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopBarDefault(text = "NearBy Restaurant")
        }
    ) {
        val allRestaurants by restaurantViewModel.allRestaurantEntity.observeAsState(initial = emptyList())

        val innerPadding = it
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            columns = StaggeredGridCells.Fixed(2)
        ) {
            items(allRestaurants.size) { restaurantItem ->
                CardRestaurant(allRestaurants[restaurantItem], modifier)
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

