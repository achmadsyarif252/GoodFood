package com.example.goodfood.presentation.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R
import com.example.goodfood.domain.model.PaymentMethod
import com.example.goodfood.domain.model.listPaymentMethod
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopBar(text = "Checkout")
        }
    )
    {
        val padding = it
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 22.dp, vertical = 22.dp)
        ) {
            Location()
            Spacer(modifier = Modifier.height(32.dp))
            PaymentMethod()
        }
    }
}

@Composable
fun PaymentMethod(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "Payment", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn {
            items(listPaymentMethod.size) {
                CardPaymentMethod(listPaymentMethod[it])
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        DetailPayment()
    }
}

@Composable
fun DetailPayment(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Food Price", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "$ 9.70", fontWeight = FontWeight.ExtraLight, fontSize = 28.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Delivery Fee", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "$ 1.20", fontWeight = FontWeight.ExtraLight, fontSize = 28.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Total Fee", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "$ 10.90", fontWeight = FontWeight.ExtraLight, fontSize = 28.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Gold
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(text = "Confirm payment")
        }
    }
}

@Composable
private fun CardPaymentMethod(paymentMethod: PaymentMethod) {
    OutlinedButton(
        contentPadding = PaddingValues(6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier.padding(vertical = 8.dp),

        onClick = { /*TODO*/ },
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = true, onClick = { /*TODO*/ }, colors = RadioButtonDefaults.colors(
                    selectedColor = Gold,
                    unselectedColor = Color.White,
                )
            )
            Text(
                text = paymentMethod.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = paymentMethod.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(40.dp)
            )
        }
    }
}

@Composable
fun Location(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Home", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(
                    text = "Jalan Otista 3 No 43,Jakarta Timur",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                )
            }
            OutlinedButton(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .width(50.dp)
                    .height(20.dp)
                    .align(Alignment.Top),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Gold,
                    containerColor = Color.White
                ),
                border = BorderStroke(
                    1.dp, brush = Brush.linearGradient(
                        colors = listOf(Gold, Gold)
                    )
                ),
                onClick = { /*TODO*/ }) {
                Text(text = "Change", fontSize = 8.sp)
            }
        }

    }
}