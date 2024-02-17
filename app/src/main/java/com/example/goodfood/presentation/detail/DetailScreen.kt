package com.example.goodfood.presentation.detail

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.component.AddMinQty
import com.example.goodfood.presentation.component.RatingDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, foodIndex: String) {
    val food = listFood[foodIndex.toInt()]
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val ctx = LocalContext.current

    var counter by remember {
        mutableIntStateOf(1)
    }
    // Membuat state dialogOpen sebagai MutableState<Boolean> dengan nilai awal false
    var dialogOpen by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingButton()
        },
        topBar = {
            TopAppBarDetail(navController, showDialog = {
                dialogOpen = true
            })
        }
    ) {
        val padding = it
        Body(food, counter, isExpanded)
        Box {
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
                foodName = food.name,
                foodImage = food.image,
                dialogOpen = dialogOpen,
            )
        }
    }
}

@Composable
private fun Body(
    food: Food,
    counter: Int,
    isExpanded: Boolean,
) {
    val ctx = LocalContext.current
    var counter1 by remember {
        mutableStateOf(counter)
    }
    var isExpanded1 by remember {
        mutableStateOf(isExpanded)
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp, top = 64.dp)
            .fillMaxSize()
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
            AddMinQty(counter = counter1, onAddCounter = { counter1++ }, onMinCounter = {
                if (counter1 > 0) counter1--
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
        InfoDetail(food)

    }
}

@Composable
private fun InfoDetail(food: Food) {
    Row {
        Text(text = "Delivery Time", fontSize = 18.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
        Text(text = "30 Min", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
    Spacer(modifier = Modifier.height(48.dp))
    Text(text = "Total Price")
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "$ ${food.price}", fontWeight = FontWeight.Bold, fontSize = 38.sp)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarDetail(navController: NavController, showDialog: () -> Unit) {
    var ctx = LocalContext.current
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
private fun FloatingButton() {
    IconButton(
        modifier = Modifier.size(40.dp),
        onClick = { /*TODO*/ }) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.Default.ShoppingCart, contentDescription = null
        )
    }
}