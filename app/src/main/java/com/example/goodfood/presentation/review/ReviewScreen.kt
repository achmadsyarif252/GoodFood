package com.example.goodfood.presentation.review

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.R
import com.example.goodfood.presentation.ReviewViewModel
import com.example.goodfood.data.SimpleDataDummy
import com.example.goodfood.domain.model.Review
import com.example.goodfood.presentation.component.TopBarDefault
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(modifier: Modifier = Modifier, reviewViewModel: ReviewViewModel = viewModel()) {
    val ctx = LocalContext.current
    val reviews by reviewViewModel.allReview.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopBarDefault(text = "Review")
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
            if (reviews.isNotEmpty())
                LazyColumn {
                    items(reviews.size) {
                        val dismissState = rememberDismissState()
                        if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                            Toast.makeText(ctx, "Review Dihapus", Toast.LENGTH_SHORT).show()
                            reviewViewModel.delete(reviews[it])
                        }

                        SwipeToDismiss(state = dismissState, background = {
                            val backgroundColor by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.8f)
                                    else -> Color.White
                                }
                            )

                            //iconSize
                            val iconScale by animateFloatAsState(targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) 1.3f else 0.5f)
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = backgroundColor)
                                    .padding(end = 16.dp), // inner padding
                                contentAlignment = Alignment.Center // place the icon at the end (left)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .scale(iconScale)
                                        .size(50.dp),
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White,

                                    )
                            }

                        }, dismissContent = {
                            CardReview(review = reviews[it])
                        }, directions = setOf(DismissDirection.EndToStart))

                    }
                }
            else
                Column(
                    modifier = Modifier
                        .padding(vertical = 60.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.noreview),
                        contentDescription = "Empty Review"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "There is no review yet", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Let's add some :)", fontSize = 24.sp)
                }
        }

    }

}

@Composable
fun CardReview(modifier: Modifier = Modifier, review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
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
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = review.food.image),
                    contentDescription = "Food Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(120.dp)
                        .clip(
                            shape = RectangleShape
                        ),
                )
            }
            Text(
                modifier = Modifier.padding(16.dp),
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
        CardReview(review = SimpleDataDummy.listReview[0])
    }
}