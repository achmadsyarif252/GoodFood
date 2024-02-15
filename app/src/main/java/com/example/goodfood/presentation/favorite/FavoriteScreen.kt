package com.example.goodfood.presentation.favorite

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.LocalNavController
import com.example.goodfood.R
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val controller = LocalNavController.current
    var isFoodSelected by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopBar(text = "Favorite")
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
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(listFood.size) {
                    CardFavorite(food = listFood[it])
                }
            }
        }
    }
}

@Composable
fun CardFavorite(
    modifier: Modifier = Modifier,
    food: Food = Food(
        name = "Bibimbap",
        totalRestaurant = 100,
        image = R.drawable.bibimbap
    ),
) {
    val navController = LocalNavController.current
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                navController.navigate(
                    "detail/${
                        (listFood
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
        CardFavorite()
    }
}