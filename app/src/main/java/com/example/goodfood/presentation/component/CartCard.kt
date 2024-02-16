package com.example.goodfood.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.data.SimpleDataDummy
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.Transaction
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.Gold

@Composable
fun CartCard(
    modifier: Modifier = Modifier,
    food: Food,
    total: Int,
    onAddQty: (total: Double) -> Unit,
    onMinQty: (total: Double) -> Unit,
) {
    var qty by remember {
        mutableIntStateOf(total)
    }
    val transactionList = SimpleDataDummy.transactionList
    val sameFood = transactionList.find { it.food == food }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Row(
            modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = food.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(100.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp, top = 4.dp, bottom = 4.dp)
            ) {
                Text(text = food.name, fontSize = 25.sp)
                Text(text = food.type, fontSize = 18.sp)
                Text(text = "Price : $ ${food.price}", fontWeight = FontWeight.Bold)
                AddMinQty(counter = qty, onAddCounter = {
                    if (sameFood != null) {
                        transactionList.find { it.food == food }!!.total += 1
                        onAddQty(transactionList.sumOf { it.food.price * it.total })
                        qty++
                    }
                }, onMinCounter = {
                    if (qty > 0 && sameFood != null) {
                        transactionList.find { it.food == food }!!.total -= 1
                        onMinQty(transactionList.sumOf { it.food.price * it.total })
                        qty--
                    }
                })
            }
        }
    }
}