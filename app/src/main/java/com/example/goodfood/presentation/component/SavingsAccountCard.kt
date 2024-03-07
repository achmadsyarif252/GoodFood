package com.example.goodfood.presentation.component

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.presentation.payment.WalletViewModel
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.core.utils.FoodViewModelFactory
import com.example.goodfood.ui.theme.FoodAppsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingsAccountCard(wallet: MyWallet) {
    val factory = FoodViewModelFactory.getInstance()
    val walletViewModel: WalletViewModel = viewModel(factory = factory)
    // Define the card's background gradient colors and direction
    val gradientColors = listOf(Color(0xFF3D5AFE), Color(0xFF3D5AFE))
    val gradient = Brush.horizontalGradient(colors = gradientColors)

    var isDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val ctx = LocalContext.current

    TopUpDialog(
        onDismiss = {
            isDialogOpen = false
        },
        onSubmit = { a, b, c ->
            if (b == "020502") {
                val prevAmount = c.totalSaldo
                walletViewModel.update(wallet.copy(totalSaldo = prevAmount + a))
                isDialogOpen = false
                Toast.makeText(ctx, "TopUp To ${wallet.wallet.name} Success", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(ctx, "PIN Wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        },
        wallet = wallet,
        dialogOpen = isDialogOpen
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = {
            isDialogOpen = true
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(brush = gradient)
        ) {
            // Assuming the VISA logo is at the bottom end of the card
            Image(
                painter = painterResource(id = wallet.wallet.image), // Replace with your actual VISA logo resource ID
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(100.dp) // Adjust the size as needed
                    .graphicsLayer {
                        // Apply alpha transparency for a shadow-like effect
                        alpha = 0.7f
                    }
            )
            // Arrange your text elements according to the design
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Savings Account",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = "4567 0987 1234 5678",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Balance",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
                Text(
                    text = "$ ${wallet.totalSaldo}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SavingAccountCardPreview() {
    FoodAppsTheme {
//        SavingsAccountCard()
    }
}
