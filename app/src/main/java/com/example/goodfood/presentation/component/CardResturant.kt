package com.example.goodfood.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goodfood.core.domain.model.Restaurant
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.nearby_restaurant.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardRestaurant(
    restaurant: Restaurant,
    modifier: Modifier,
) {
    val restaurantViewModel: RestaurantViewModel = hiltViewModel()

    val navController = LocalNavController.current
    val allRestaurant by restaurantViewModel.allRestaurantEntity.observeAsState(initial = emptyList())
    val index = allRestaurant.indexOf(restaurant)

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
            .wrapContentWidth()
            .wrapContentHeight(),
        onClick = {
            navController.navigate("detailresto/${index}")
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = painterResource(id = restaurant.photo),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    ),
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = restaurant.name,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp),
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.Red
                        )
                        Text(
                            text = restaurant.location,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(top = 4.dp),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = restaurant.rating,
                        fontSize = 13.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
