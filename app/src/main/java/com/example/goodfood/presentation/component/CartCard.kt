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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.presentation.TransactionViewModel
import com.example.goodfood.domain.model.Food

@Composable
fun CartCard(
    modifier: Modifier = Modifier,
    food: Food,
    total: Int,
    transactionViewModel: TransactionViewModel = viewModel()
) {
    val transactionList by transactionViewModel.allTransaction!!.observeAsState()
    val item = transactionList!!.find { it!!.food == food }
    var qty = item?.total ?: 0

    val ctx = LocalContext.current

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
                    if (item != null) {
                        transactionViewModel.update(item.copy(total = item.total + 1))
                        qty++
                    }
                }, onMinCounter = {
                    if (qty > 0) {
                        transactionViewModel.update(item!!.copy(total = item.total - 1))
                        qty--

                    }
                    if (qty == 0) {
                        transactionViewModel.delete(item!!)
                    }

                })
            }
        }
    }
}