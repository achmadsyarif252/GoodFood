package com.example.goodfood.presentation.topupscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.core.utils.FoodViewModelFactory
import com.example.goodfood.presentation.payment.WalletViewModel
import com.example.goodfood.presentation.component.SavingsAccountCard
import com.example.goodfood.presentation.component.TopBarDefault

@Composable
fun SavingAccountScreen(
    modifier: Modifier = Modifier,
) {
    val factory = FoodViewModelFactory.getInstance()
    val paymentMethodViewModel: WalletViewModel = viewModel(factory = factory)
    val allPaymentMethod by paymentMethodViewModel.allWallet.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopBarDefault(text = "Saving Account")
        }
    ) {
        val innerPadding = it
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(allPaymentMethod.size) { myWallet ->
                SavingsAccountCard(allPaymentMethod[myWallet])
            }
        }
    }
}