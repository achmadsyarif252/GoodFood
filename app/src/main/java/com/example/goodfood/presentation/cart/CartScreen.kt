package com.example.goodfood.presentation.cart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.LocalNavController
import com.example.goodfood.R
import com.example.goodfood.data.SimpleDataDummy
import com.example.goodfood.presentation.component.CartCard
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val products by remember {
        mutableStateOf(SimpleDataDummy.transactionList)
    }
    val navController = LocalNavController.current
    var subTotal by remember {
        mutableDoubleStateOf(products.sumOf { it.food.price * it.total })
    }
    var shippingFee by remember {
        mutableDoubleStateOf(if (subTotal > 0) 1.2 else 0.0)
    }
    var total by remember {
        mutableDoubleStateOf(subTotal + shippingFee)
    }

    val ctx = LocalContext.current
    Scaffold(
        containerColor = CardFood,
        topBar = {
            TopBar(text = "My Cart")
        }
    ) {
        val padding = it
        Column(modifier.wrapContentHeight()) {
            if (products.isNotEmpty())
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxHeight(0.55f)
                ) {
                    items(products.count()) { food ->
                        CartCard(
                            food = products[food].food,
                            total = products[food].total,
                            onAddQty = {
                                Toast.makeText(ctx, "Add Qty", Toast.LENGTH_SHORT).show()
                                subTotal = it
                                total = subTotal + shippingFee
                            },
                            onMinQty = {
                                subTotal = it
                                if (subTotal <= 0) {
                                    shippingFee = 0.0
                                }
                                total = subTotal + shippingFee
                            },
                            removeFood = { deletedFood ->
                                products.removeAt(food)
                            }
                        )
                    }
                }
            else {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                        .fillMaxHeight(0.55f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(id = R.drawable.bibimbap),
                        contentDescription = null
                    )
                    Text(
                        text = "Cart still empty :(",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Lets Add Something here",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )


                }
            }
            CouponCode()
            Spacer(modifier = Modifier.height(16.dp))
            DetailPayment(type = "Sub Total", total = "$ $subTotal")
            DetailPayment(type = "Shipping", total = "$ $shippingFee")
            DetailPayment(type = "Total", total = "$ $total")
            Spacer(modifier = Modifier.height(8.dp))
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