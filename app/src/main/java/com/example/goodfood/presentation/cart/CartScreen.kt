package com.example.goodfood.presentation.cart

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.R
import com.example.goodfood.presentation.FoodViewModelFactory
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.component.CartCard
import com.example.goodfood.presentation.component.TopBarDefault
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
) {
    val factory = FoodViewModelFactory.getInstance()
    val transactionViewModel: TransactionViewModel = viewModel(factory = factory)

    val allTransaction by transactionViewModel.allTransaction.observeAsState()
    val subTotal by transactionViewModel.getSubTotal().collectAsState(initial = 0.0)

    var shippingFee = if (subTotal > 0.0) 1.2 else 0.0
    var total = subTotal + shippingFee

    val navController = LocalNavController.current


    val ctx = LocalContext.current
    Scaffold(
        containerColor = CardFood,
        topBar = {
            TopBarDefault(text = "My Cart")
        }
    ) {
        val padding = it
        allTransaction?.let { transactions ->
            if (transactions.isEmpty()) {
                Column(
                    modifier = Modifier
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
        }
        Column(modifier.wrapContentHeight()) {
            allTransaction?.let {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxHeight(0.55f)
                ) {
                    allTransaction?.count()?.let { transaction ->
                        items(transaction) { food ->
                            CartCard(
                                food = allTransaction!![food].food,
                                total = allTransaction!![food].total,
                            )
                        }
                    }
                }
                CouponCode()
                Spacer(modifier = Modifier.height(16.dp))
                DetailPayment(type = "Sub Total", total = "$ $subTotal")
                DetailPayment(type = "Shipping", total = "$ $shippingFee")
                DetailPayment(type = "Total", total = "$ $total")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    enabled = total > 0,
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