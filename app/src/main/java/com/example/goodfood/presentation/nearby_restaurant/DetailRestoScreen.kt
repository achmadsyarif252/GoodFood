package com.example.goodfood.presentation.nearby_restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.data.SimpleDataDummy
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.model.restaurants
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@Composable
fun DetailResto(restaurant: Restaurant) {
    var isFav by remember {
        mutableStateOf(SimpleDataDummy.listFavoriteResto.contains(restaurant))
    }
    Scaffold(
        topBar = {
            TopBar(text = restaurant.name)
        },
        floatingActionButton = {
            IconButton(
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = {
                    isFav = !isFav
                    if (isFav) {
                        SimpleDataDummy.listFavoriteResto.add(restaurant)
                    } else {
                        SimpleDataDummy.listFavoriteResto.remove(restaurant)
                    }
                }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = if (!isFav) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    ) {
        val padding = it
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    8.dp
                ),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .wrapContentHeight(),
                    painter = painterResource(id = restaurant.photo),
                    contentDescription = restaurant.name
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Since : ${restaurant.establishDate}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Ratings",
                            tint = Gold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = restaurant.rating,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = restaurant.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = restaurant.location, tint = Color.Red,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = restaurant.location,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = restaurant.description)
        }
    }
}

@Preview
@Composable
private fun DetailRestoPreview() {
    FoodAppsTheme {
        DetailResto(restaurant = restaurants[0])
    }
}