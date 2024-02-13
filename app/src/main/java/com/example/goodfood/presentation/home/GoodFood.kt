package com.example.goodfood.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.bottombar.BottomNavigation
import com.example.goodfood.presentation.component.CardBestDishes
import com.example.goodfood.presentation.component.CardFoodCarousel
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.FoodAppsTheme

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) { innerPadding ->
        Body(
            modifier = Modifier.padding(innerPadding), navController = navController
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}


@Composable
fun Body(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier.padding(horizontal = 22.dp, vertical = 32.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(32.dp))
        FoodCarouselSection(navController = navController)
        Spacer(modifier = Modifier.height(32.dp))
        BestDishes()
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Delicious Foods", fontWeight = FontWeight.Bold, fontSize = 38.sp)
        Text(text = "We made fresh and tasty foods", fontSize = 16.sp)
    }
}

@Composable
fun FoodCarouselSection(modifier: Modifier = Modifier, navController: NavController) {
    LazyRow(
    ) {
        items(listFood.size) {
            CardFoodCarousel(
                food = listFood[it],
                cardColor = CardFood,
                navController = navController
            )
        }
    }
}

@Composable
fun BestDishes(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Best Restaurant Dished", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(32.dp))
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(listFood.size) {
                CardBestDishes(food = listFood[it])
            }
        }
    }
}


@Composable
fun FoodDescription(modifier: Modifier = Modifier, food: Food) {
    Column(Modifier.padding(start = 8.dp)) {
        Text(text = food.type, fontSize = 14.sp)
        Text(text = food.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "$ ${food.price}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun TopBarPreview() {
    FoodAppsTheme {
        TopBar()
    }
}