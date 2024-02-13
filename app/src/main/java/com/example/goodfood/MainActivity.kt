package com.example.goodfood

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.bottombar.BottomNavigation
import com.example.goodfood.ui.theme.CardFood
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.Gold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppsTheme {
                FoodApp()
            }
        }
    }
}

@Composable
fun FoodApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) { innerPadding ->
        Body(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun FoodAppPreview() {
    FoodApp()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
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
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(horizontal = 22.dp, vertical = 32.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(32.dp))
        FoodCarouselSection()
        Spacer(modifier = Modifier.height(32.dp))
        BestDishes()
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Delicious Foods", fontWeight = FontWeight.Bold, fontSize = 38.sp)
        Text(text = "We made fresh and tasty foods", fontSize = 16.sp)
    }
}

@Composable
fun FoodCarouselSection(modifier: Modifier = Modifier) {
    LazyRow(
    ) {
        items(listFood.size) {
            CardFoodCarousel(food = listFood[it], cardColor = CardFood)
        }
    }
}

@Composable
fun BestDishes(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Best Restaurant Dished", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(32.dp))
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(listFood.size) {
                CardBestDishes(food = listFood[it])
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBestDishes(modifier: Modifier = Modifier, food: Food) {
    val ctx = LocalContext.current
    Column {
        Box(contentAlignment = Alignment.BottomEnd) {
            Card(
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 16.dp)
                    .width(160.dp)
                    .height(160.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(
                    8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(120.dp),
                        alignment = Alignment.Center,
                        painter = painterResource(id = food.image), contentDescription = null,
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {
                    Toast
                        .makeText(ctx, "Added", Toast.LENGTH_SHORT)
                        .show()
                },
                shape = CircleShape, modifier = Modifier
                    .align(
                        Alignment.BottomEnd
                    )
                    .offset(x = (-35).dp)
                    .size(40.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier,
                    )
                }
            }
        }
        FoodDescription(food = food)
        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Composable
fun FoodDescription(modifier: Modifier = Modifier, food: Food) {
    Column(Modifier.padding(start = 8.dp)) {
        Text(text = food.type, fontSize = 14.sp)
        Text(text = food.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "$ ${food.price}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun CardFoodCarousel(modifier: Modifier = Modifier, food: Food, cardColor: Color) {
    Card(
        modifier
            .width(170.dp)
            .height(160.dp)
            .padding(end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = food.image),
                contentDescription = null,
                modifier = modifier.size(80.dp)
            )
            Text(
                text = food.name,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                textAlign = TextAlign.Center,
                text = "${food.totalRestaurant} Restaurants",
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun TopBarPreview() {
    FoodAppsTheme {
        TopBar()
    }
}
