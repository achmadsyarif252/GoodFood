package com.example.goodfood.presentation.payment

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.core.data.source.local.entity.MyWallet
import com.example.goodfood.core.data.source.local.entity.PaymentMethod
import com.example.goodfood.presentation.FoodViewModelFactory
import com.example.goodfood.presentation.cart.TransactionViewModel
import com.example.goodfood.presentation.component.TopBarDefault
import com.example.goodfood.ui.theme.Gold

@Composable
fun SuccessDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            // Custom design for dialog
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Animation
                AnimatedVisibility(
                    visible = showDialog,
                    enter = scaleIn(animationSpec = tween(durationMillis = 300))
                ) {
                    // This is where your success message or icon would go
                    AlertDialog(
                        onDismissRequest = onDismiss,
                        title = { Text("Success") },
                        text = { Text("Have a nice eat,order completed successfully.") },
                        confirmButton = {
                            Button(onClick = onDismiss) {
                                Text("OK")
                            }
                        },
                        containerColor = Color.White,
                        textContentColor = Color.Black,
                    )
                }
            }
        }
    }
}

@Composable
fun FailedDialog(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedVisibility(
                    visible = showDialog,
                    enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 300))
                ) {
                    AlertDialog(
                        onDismissRequest = onDismiss,
                        title = { Text("Failed", color = Color.White) },
                        text = {
                            Text("Your ballance is not eneough,please top up first.")
                        },
                        confirmButton = {
                            Button(onClick = onDismiss) {
                                Text("OK", color = Color.White)
                            }
                        },
                        containerColor = Color(0xFFB00020), // Use a red color to indicate failure
                        textContentColor = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {


    Scaffold(
        topBar = {
            TopBarDefault(text = "Checkout")
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
    val factory = FoodViewModelFactory.getInstance()
    val walletViewModel: WalletViewModel = viewModel(factory = factory)

    val allWallet by walletViewModel.allWallet.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "Payment", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(32.dp))
        if (allWallet.isNotEmpty()) {
            var selectedOption by remember {
                mutableStateOf(allWallet[0].wallet)
            }
            var selectedWallet by remember {
                mutableStateOf(allWallet[0])
            }
            LazyColumn {
                items(allWallet.size) {
                    CardPaymentMethod(
                        allWallet[it].wallet,
                        selectedOption = selectedOption,
                        onOptionSelected = { paymentMethod ->
                            selectedOption = paymentMethod
                        })
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            allWallet.find { it.wallet == selectedOption }?.let { DetailPayment(myWallet = it) }
        }
    }

}


@Composable
fun DetailPayment(
    modifier: Modifier = Modifier,
    myWallet: MyWallet,
) {
    val factory = FoodViewModelFactory.getInstance()
    val transactionViewModel: TransactionViewModel = viewModel(factory = factory)
    val walletViewModel: WalletViewModel = viewModel(factory = factory)
    val foodPrice by transactionViewModel.getSubTotal().collectAsState(initial = 0.0)
    val shippingFee = if (foodPrice > 0.0) 1.2 else 0.0
    val total = foodPrice + shippingFee

    var showDialog by remember {
        mutableStateOf(false)
    }
    var showFailedDialog by remember {
        mutableStateOf(false)
    }

    SuccessDialog(showDialog = showDialog) {
        showDialog = false
        transactionViewModel.deleteAll()
    }
    FailedDialog(showDialog = showFailedDialog) {
        showFailedDialog = false
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextDetail("Food Price", "$ $foodPrice")
        TextDetail("Total Fee", "$ $shippingFee")
        TextDetail("Total", "$ $total")
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
            onClick = {
                if (total <= myWallet.totalSaldo) {
                    myWallet.let {
                        walletViewModel.update(wallet = it.copy(totalSaldo = it.totalSaldo - total))
                        showDialog = true
                    }
                } else {
                    showFailedDialog = true
                }
            }) {
            Text(text = "Confirm payment")
        }
    }
}

@Composable
private fun TextDetail(infoType: String, totalPrice: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = infoType, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = totalPrice, fontWeight = FontWeight.ExtraLight, fontSize = 28.sp)
    }
}

@Composable
fun CardPaymentMethod(
    paymentOption: PaymentMethod, // Menambahkan parameter opsi pembayaran
    selectedOption: PaymentMethod, // Menambahkan parameter opsi yang dipilih
    onOptionSelected: (PaymentMethod) -> Unit // Menambahkan parameter fungsi untuk memperbarui opsi yang dipilih
) {
    OutlinedButton(
        contentPadding = PaddingValues(6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier.padding(vertical = 8.dp),

        onClick = { onOptionSelected(paymentOption) },
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Log.d("Yang Oke", "${paymentOption == selectedOption}")
            RadioButton(
                // Mengatur parameter selected sesuai dengan opsi yang dipilih
                selected = paymentOption == selectedOption,
                // Mengatur parameter onClick untuk memanggil fungsi onOptionSelected
                onClick = {
                    Log.d("YANG DI CARDPAYMENT", paymentOption.name)
                    onOptionSelected(paymentOption)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Gold,
                    unselectedColor = Color.LightGray,
                )
            )
            Text(
                text = paymentOption.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = paymentOption.image),
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