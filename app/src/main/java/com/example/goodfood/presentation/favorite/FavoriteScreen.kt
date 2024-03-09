package com.example.goodfood.presentation.favorite

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.R
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.presentation.FoodViewModelFactory
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.component.CardRestaurant
import com.example.goodfood.presentation.component.TopBarDefault
import com.example.goodfood.presentation.home.FoodViewModel
import com.example.goodfood.presentation.nearby_restaurant.RestaurantViewModel
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    LocalNavController.current
    var isFoodSelected by remember {
        mutableStateOf(true)
    }
    Scaffold(
        topBar = {
            TopBarDefault(text = "Favorite")
        }
    ) {
        val padding = it
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Row {
                ButtonHeader(
                    modifier = Modifier.weight(1f),
                    onClick = { isFoodSelected = true },
                    title = "Food",
                    containerColor = if (isFoodSelected) Gold else Color.White,
                    contentColor = if (isFoodSelected) Color.White else Gold
                )
                Spacer(modifier = Modifier.width(10.dp))
                ButtonHeader(
                    modifier = Modifier.weight(1f),
                    onClick = { isFoodSelected = false },
                    title = "Restaurant",
                    containerColor = if (isFoodSelected) Color.White else Gold,
                    contentColor = if (isFoodSelected) Gold else Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            FavoriteScreenContent(isFoodSelected = isFoodSelected)

        }
    }
}

@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    isFoodSelected: Boolean,
) {
    val factory = FoodViewModelFactory.getInstance()
    val foodViewModel: FoodViewModel = hiltViewModel()
    val restaurantViewModel: RestaurantViewModel = viewModel(factory = factory)

    val allFoods by foodViewModel.allFood.observeAsState(initial = emptyList())
    val listFavFood = allFoods.filter { it.isFavorite }

    val allRestaurant by restaurantViewModel.allRestaurantEntity.observeAsState(initial = emptyList())
    val listFavResto = allRestaurant.filter { it.isFavorite }

    if (isFoodSelected && listFavFood.isNotEmpty())
        LazyColumn {
            items(listFavFood.size) {
                CardFavorite(food = listFavFood[it])
            }
        }

    if (isFoodSelected && listFavFood.isEmpty()) {
        EmptyContent(
            image = R.drawable.emptyfavfood,
            "Favorite Food Still Empty",
            "Let's Add Some :)"
        )
    }

    if (!isFoodSelected && listFavResto.isNotEmpty()) {
        LazyColumn {
            items(listFavResto.size) {
                CardRestaurant(restaurant = listFavResto[it], modifier = Modifier)
            }
        }
    }

    if (!isFoodSelected && listFavResto.isEmpty()) {
        EmptyContent(image = R.drawable.emptyrestofav, "Nothing Here :(", "Add Your Favorite One")
    }
}

@Composable
private fun EmptyContent(@DrawableRes image: Int, text: String, subText: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Column(
            modifier = Modifier.fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Empty Content",
            )
            Text(text = text, fontSize = 22.sp)
            Text(text = subText, fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
    }
}

@Composable
fun ButtonHeader(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    containerColor: Color,
    contentColor: Color
) {
    OutlinedButton(
        border = BorderStroke(
            1.dp, brush = Brush.linearGradient(
                colors = listOf(
                    Gold,
                    Gold,
                )
            )
        ),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        onClick = { onClick() }, shape = RoundedCornerShape(24.dp)
    ) {
        Text(text = title)
    }
}

@Composable
fun CardFavorite(
    modifier: Modifier = Modifier,
    food: Food,
) {
    val factory = FoodViewModelFactory.getInstance()
    val foodViewModel: FoodViewModel = hiltViewModel()

    val navController = LocalNavController.current
    val allFood by foodViewModel.allFood.observeAsState()

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                navController.navigate(
                    "detail/${
                        (allFood!!
                            .indexOf(food)
                            .toString())
                    }"
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.mcdonalds),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(25.dp)
                        .offset((-5).dp)
                )
            }

            Image(
                painter = painterResource(id = food.image),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = food.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Gold
                        )
                    }
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "(${food.totalRestaurant})", fontSize = 12.sp)
                }
                Text(text = "$ ${food.price}", fontSize = 22.sp, color = Gold)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardFavoritePreview() {
    FoodAppsTheme {
        CardFavorite(food = Food(0, 0, "", 1, "", 12.0, "", false))
    }
}