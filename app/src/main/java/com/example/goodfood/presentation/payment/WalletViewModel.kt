package com.example.goodfood.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.usecase.WalletUseCase
import kotlinx.coroutines.launch

class WalletViewModel(private val walletUseCase: WalletUseCase) : ViewModel() {
    val allWallet: LiveData<List<MyWallet>> = walletUseCase.getAllWallet().asLiveData()

    init {
        insertInitialPayment()
    }

    private fun insertInitialPayment() = viewModelScope.launch {
        if (walletUseCase.isWalletListEmpty()) {
            walletUseCase.insertAllWallet()
        }
    }

    fun insertWallet(wallet: MyWallet) = viewModelScope.launch {
        walletUseCase.insert(wallet)
    }

    fun delete(wallet: MyWallet) = viewModelScope.launch {
        walletUseCase.delete(wallet)
    }

    fun update(wallet: MyWallet) = viewModelScope.launch {
        walletUseCase.update(wallet)
    }
}