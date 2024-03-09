package com.example.goodfood.presentation.detail

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.model.Review
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.cart.TransactionViewModel
import com.example.goodfood.presentation.component.AddMinQty
import com.example.goodfood.presentation.component.RatingDialog
import com.example.goodfood.presentation.home.FoodViewModel
import com.example.goodfood.presentation.review.CardReview
import com.example.goodfood.presentation.review.ReviewViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    foodIndex: String,
) {
    val foodViewModel: FoodViewModel = hiltViewModel()
    val reviewViewModel: ReviewViewModel = hiltViewModel()

    val allFoods by foodViewModel.allFood.observeAsState(initial = emptyList())
    val food = allFoods.getOrNull(foodIndex.toInt())

    var isFavoriteFood = food?.isFavorite ?: false
    var isExpanded by remember {
        mutableStateOf(false)
    }


    val allReview by reviewViewModel.allReview.observeAsState(initial = emptyList())
    var listReview by remember {
        mutableStateOf(allReview.filter {
            it?.let {
                it.food == food
            } == true
        })
    }
    LaunchedEffect(key1 = allReview) {
        listReview = allReview.filter {
            it?.let {
                it.food == food
            } == true
        }
    }
    val ctx = LocalContext.current

    var counter by remember {
        mutableIntStateOf(1)
    }
    // Membuat state dialogOpen sebagai MutableState<Boolean> dengan nilai awal false
    var dialogOpen by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            food?.let {
                FloatingButton(it, counter)
            }
        },
        topBar = {
            TopAppBarDetail(navController, showDialog = {
                dialogOpen = true
            })
        }
    ) {
        val padding = it
        if (food != null) {
            Body(
                padding,
                food,
                counter,
                isExpanded,
                isFavFood = isFavoriteFood,
                listReview = listReview,
                updateCounter = {
                    counter = it
                })
        }
        Box {
            if (food != null) {
                RatingDialog(
                    onDismiss = {
                        dialogOpen = false
                    },
                    onSubmit = {
                        Toast.makeText(
                            ctx,
                            "Terima Kasih atas review anda",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialogOpen = false
                    },
                    food = food,
                    dialogOpen = dialogOpen,
                )
            }
        }
    }
}

@Composable
private fun Body(
    padding: PaddingValues,
    food: Food,
    counter: Int,
    isExpanded: Boolean,
    isFavFood: Boolean,
    updateCounter: (Int) -> Unit,
    listReview: List<Review?>
) {

    var counter1 by remember {
        mutableIntStateOf(counter)
    }
    var isExpanded1 by remember {
        mutableStateOf(isExpanded)
    }


    val navController = LocalNavController.current

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(padding)
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = food.image),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)


        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = food.name, fontSize = 42.sp, fontWeight = FontWeight.Bold)
            AddMinQty(counter = counter1, onAddCounter = {
                counter1++
                updateCounter(counter1)
            }, onMinCounter = {
                if (counter1 > 0) {
                    counter1--
                    updateCounter(counter1)
                }
            })

        }
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(300),
                )
                .clickable {
                    isExpanded1 = !isExpanded1
                },

            text = food.deskripsi,
            maxLines = if (isExpanded1) 100 else 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(16.dp))
        InfoDetail(food = food, isFav = isFavFood)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Review")
            Text(text = "See all", color = Color.Blue, modifier = Modifier.clickable {
                navController.navigate("review")
            })
        }
        LazyRow {
            items(listReview.size) {
                listReview[it]?.let { it1 -> CardReview(review = it1) }
            }
        }
    }
}

@Composable
private fun InfoDetail(food: Food, isFav: Boolean) {
    val foodViewModel: FoodViewModel = hiltViewModel()
    val ctx = LocalContext.current
    val allFood by foodViewModel.allFood.observeAsState(initial = emptyList())

    val isFavorite = allFood.find { it == food }?.isFavorite ?: false

    Row {
        Text(text = "Delivery Time", fontSize = 18.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
        Text(text = "30 Min", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
    Spacer(modifier = Modifier.height(48.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(text = "Total Price")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "$ ${food.price}", fontWeight = FontWeight.Bold, fontSize = 38.sp)
        }
        IconButton(
            modifier = Modifier.offset(y = (-8).dp, x = (-8).dp),
            onClick = {
                Toast.makeText(ctx, "isFavorite : $isFavorite", Toast.LENGTH_SHORT).show()
                if (isFavorite == true) {
                    foodViewModel.update(food = food.copy(isFavorite = false))

                } else {
                    foodViewModel.update(food = food.copy(isFavorite = true))
                }
            }) {
            Icon(
                modifier = Modifier.size(40.dp),
                tint = Color.Red,
                imageVector = if (isFavorite == false) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = null
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarDetail(navController: NavController, showDialog: () -> Unit) {
    var showDropDown by remember { mutableStateOf(false) }

    TopAppBar(title = { /*TODO*/ }, actions = {
        DropdownMenu(
            offset = DpOffset((-122).dp, (-66).dp),
            expanded = showDropDown, onDismissRequest = { showDropDown = false }

        ) {
            DropdownMenuItem(text = { Text(text = "Tulis Review") }, leadingIcon = {
            }, onClick = {
                showDialog()
                showDropDown = false
            })
        }
        IconButton(onClick = {
            showDropDown = true
        }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }

    }, navigationIcon = {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }
    })
}

@Composable
private fun FloatingButton(food: Food, total: Int) {
    val transactionViewModel: TransactionViewModel = hiltViewModel()
    val transaction by transactionViewModel.allTransaction.observeAsState(initial = emptyList())

    val ctx = LocalContext.current
    IconButton(
        modifier = Modifier.size(40.dp),
        onClick = {
            if (total > 0) {
                val totalSameItem = transaction.find { it.food == food }
                val updateTotal = totalSameItem?.total?.plus(total)

                if (totalSameItem != null) {
                    transactionViewModel.update(totalSameItem.copy(total = updateTotal ?: 1))
                } else
                    transactionViewModel.insert(
                        Transaction(
                            id = 0,
                            food = food,
                            total = total
                        )
                    )
                Toast.makeText(ctx, "Added To Cart", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(ctx, "Cart can't be empty!", Toast.LENGTH_SHORT).show()
            }
        }) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.Default.ShoppingCart, contentDescription = null
        )
    }
}