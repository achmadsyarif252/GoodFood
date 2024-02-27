package com.example.goodfood.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R
import com.example.goodfood.ui.theme.Gold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome() {
    TopAppBar(title = {
        val buildAnnotatedString = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Gold
                )
            ) {
                append("GOOD")
            }
            withStyle(
                SpanStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            ) {
                append(" FOOD")
            }
        }
        Text(text = buildAnnotatedString, modifier = Modifier.padding(start = 8.dp))
    },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 16.dp),
                painter = painterResource(id = R.drawable.bibimbap),
                contentDescription = "Menu"
            )
        },
        actions = {
            Card(
                modifier = Modifier.padding(end = 16.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.8f)
                )
            ) {
                IconButton(
                    onClick = { /*TODO*/ }) {
                    Image(
                        modifier = Modifier.size(22.dp),
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null
                    )
                }
            }
        }
    )
}