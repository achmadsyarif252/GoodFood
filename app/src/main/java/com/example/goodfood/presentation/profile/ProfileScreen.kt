package com.example.goodfood.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R
import com.example.goodfood.data.SimpleDataDummy
import com.example.goodfood.domain.model.PaymentMethod
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.ScaffoldBgColor

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(
        containerColor = ScaffoldBgColor,
        topBar = {
            TopBar(text = "Akun Saya")
        }
    ) {
        val innerPadding = it
        Body(ineerPadding = innerPadding)
    }
}

@Composable
fun Body(modifier: Modifier = Modifier, ineerPadding: PaddingValues) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(ineerPadding)
                .padding(vertical = 32.dp, horizontal = 24.dp)
        ) {
            Header()
            PointsSaldo()
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)

        ) {
            Text(text = "Hello")
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "Avatar Profile",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Achmad Syarif", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "0895384252730", fontWeight = FontWeight.Normal, fontSize = 13.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = "Edit",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun PointsSaldo(modifier: Modifier = Modifier) {
    val myWallet = SimpleDataDummy.listMyWallet
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Points", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),

            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            LazyRow(Modifier.padding(horizontal = 4.dp, vertical = 12.dp)) {
                items(myWallet.size) {
                    Wallet(paymentMethod = myWallet[it].wallet)
                }
            }
        }
    }
}

@Composable
fun Wallet(modifier: Modifier = Modifier, paymentMethod: PaymentMethod) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 14.dp)
    ) {
        Image(
            painter = painterResource(id = paymentMethod.image),
            contentDescription = paymentMethod.name,
            modifier = Modifier.size(32.dp)
        )
        Text(text = "$ 1000", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Top Up ${paymentMethod.name}", fontSize = 14.sp, fontWeight = FontWeight.Light)
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    FoodAppsTheme {
        Header()
    }
}