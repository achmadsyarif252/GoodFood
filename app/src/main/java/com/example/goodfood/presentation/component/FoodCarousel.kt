package com.example.goodfood.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.FoodViewModel
import com.example.goodfood.presentation.FoodViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFoodCarousel(
    modifier: Modifier = Modifier,
    foodIndex: Int,
    cardColor: Color,
) {
    val factory = FoodViewModelFactory.getInstance()
    val foodViewModel: FoodViewModel = viewModel(factory = factory)

    val navController = LocalNavController.current
    val allFoods by foodViewModel.allFood.observeAsState()
    val food = allFoods!![foodIndex]
    Card(
        onClick = {
            navController.navigate("detail/${foodIndex}")
            Log.d("COBLOS", "$foodIndex")
        },
        modifier = modifier
            .width(170.dp)
            .height(160.dp)
            .padding(end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),


        ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = food.image),
                contentDescription = null,
                modifier = modifier.size(80.dp)
            )
            Text(
                text = food.name,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                textAlign = TextAlign.Center,
                text = "${food.totalRestaurant} Restaurants",
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}