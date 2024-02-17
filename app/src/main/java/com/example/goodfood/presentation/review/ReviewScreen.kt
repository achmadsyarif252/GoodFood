package com.example.goodfood.presentation.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R
import com.example.goodfood.domain.model.Review
import com.example.goodfood.domain.model.reviews
import com.example.goodfood.presentation.component.TopBar
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@Composable
fun ReviewScreen(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(text = "Review")
        }, floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Gold,
                onClick = {

                }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) {
        val innerPadding = it
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            LazyColumn {
                items(reviews.size) {
                    CardReview(review = reviews[it])
                }
            }
        }

    }
}

@Composable
fun CardReview(modifier: Modifier = Modifier, review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp,
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally) // ini adalah modifier untuk membuat Text sejajar secara vertikal
                ,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = review.photo),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(
                            shape = CircleShape // ini adalah modifier untuk membuat gambar lingkaran
                        )
                        .border(
                            2.dp,
                            Color.Gray,
                            CircleShape
                        ) // ini adalah modifier untuk menambahkan border (opsional)
                        .padding(4.dp) // ini adalah modifier untuk menambahkan padding (opsional)
                        .width(30.dp)
                        .height(30.dp)

                )
                Text(text = review.name, fontSize = 18.sp, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    repeat(review.rating) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Gold
                        )
                    }
                }
            }
            Text(
                text = review.review,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Preview
@Composable
private fun CardReviewPreview() {
    FoodAppsTheme {
        CardReview(review = reviews[0])
    }
}