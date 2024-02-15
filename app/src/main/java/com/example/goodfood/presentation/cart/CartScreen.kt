package com.example.goodfood.presentation.cart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.goodfood.LocalNavController
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.component.CartCard
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.Gold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    Scaffold(
        containerColor = CardFood,
        topBar = {
            TopAppBar(title = { Text(text = "My Cart") }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            })
        }
    ) {
        val padding = it
        Column {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight(0.6f)
            ) {
                items(listFood.size) {
                    CartCard(food = listFood[it])
                }
            }
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
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                placeholder = {
                    Text(text = "Sub Total")
                },
                trailingIcon = {
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = "Rp.128.000"
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

            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                placeholder = {
                    Text(text = "Shipping")
                },
                trailingIcon = {
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = "Rp.128.000"
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

            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                placeholder = {
                    Text(text = "Total")
                },
                trailingIcon = {
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = "Rp.128.000"
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