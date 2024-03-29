package com.example.goodfood.presentation.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.cart.TransactionViewModel
import com.example.goodfood.presentation.home.FoodDescription
import com.example.goodfood.presentation.home.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBestDishes(
    modifier: Modifier = Modifier,
    foodIndex: Int,
) {
    val ctx = LocalContext.current
    val foodViewModel: FoodViewModel = hiltViewModel()
    val transactionViewModel: TransactionViewModel = hiltViewModel()

    val navController = LocalNavController.current
    val allFood by foodViewModel.allFood.observeAsState()
    val food = allFood!![foodIndex]
    val transactionList by transactionViewModel.allTransaction.observeAsState()


    Column {
        Box(contentAlignment = Alignment.BottomEnd) {
            Card(
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 16.dp)
                    .width(160.dp)
                    .height(160.dp)
                    .clickable {
                        navController.navigate("detail/${foodIndex}")
                    },
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(
                    8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(120.dp),
                        alignment = Alignment.Center,
                        painter = painterResource(id = food.image), contentDescription = null,
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {
                    val sameFood = transactionList!!.find { it.food == food }

                    if (sameFood != null) {
                        transactionViewModel.update(sameFood.copy(total = sameFood.total + 1))
                    } else {
                        transactionViewModel.insert(Transaction(food = food, total = 1))
                    }
                    Toast.makeText(ctx, "Added To Cart", Toast.LENGTH_SHORT).show()
                },
                shape = CircleShape, modifier = Modifier
                    .align(
                        Alignment.BottomEnd
                    )
                    .offset(x = (-35).dp)
                    .size(40.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier,
                    )
                }
            }
        }
        FoodDescription(food = food)
        Spacer(modifier = Modifier.height(20.dp))
    }
}