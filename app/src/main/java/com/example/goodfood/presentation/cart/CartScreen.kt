package com.example.goodfood.presentation.cart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodfood.LocalNavController
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.component.CartCard
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    Scaffold(
        containerColor = CardFood,
        topBar = {
            TopBar(text = "My Cart")
        }
    ) {
        val padding = it
        Column(modifier.wrapContentHeight()) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight(0.55f)
            ) {
                items(listFood.size) {
                    CartCard(food = listFood[it])
                }
            }
            CouponCode()
            Spacer(modifier = Modifier.height(16.dp))
            DetailPayment(type = "Sub Total", total = "Rp.128.000")
            DetailPayment(type = "Shipping", total = "Rp.12.000")
            DetailPayment(type = "Total", total = "Rp.140.000")
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Gold
                ),
                modifier = Modifier
                    .padding(horizontal = 52.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate("payment")
                }) {
                Text(text = "Payment")
            }

        }
    }
}

@Composable
fun DetailPayment(modifier: Modifier = Modifier, type: String, total: String) {
    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        placeholder = {
            Text(text = type)
        },
        trailingIcon = {
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = total
            )
        },
        value = "",
        onValueChange = {},
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .padding(horizontal = 64.dp, vertical = 6.dp)
            .fillMaxWidth()
            .height(50.dp)
    )
}

@Composable
fun CouponCode(modifier: Modifier = Modifier) {
    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        trailingIcon = {
            Button(
                modifier = Modifier
                    .fillMaxHeight(),
                onClick = { /*TODO*/ }) {
                Text(text = "Apply Coupon")
            }
        },
        value = "",
        onValueChange = {},
        shape = RoundedCornerShape(
            topEnd = 128.dp,
            bottomEnd = 128.dp,
            topStart = 32.dp,
            bottomStart = 32.dp
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(50.dp)
    )
}

@Preview
@Composable
private fun CartScreenPreview() {
    FoodAppsTheme {
        CartScreen()
    }
}