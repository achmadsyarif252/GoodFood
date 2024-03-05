package com.example.goodfood.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.WalletRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.MyWallet
import kotlinx.coroutines.launch

class WalletViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WalletRepository
    val allWallet: LiveData<List<MyWallet>>

    init {
        val walletDao = FoodDatabase.getDatabase(application).walletDao()
        repository = WalletRepository(walletDao)
        allWallet = repository.allWallet.asLiveData()
        insertInitialPayment()
    }

    private fun insertInitialPayment() = viewModelScope.launch {
        if (repository.isWalletListEmpty()) {
            repository.insertAllWallet()
        }
    }

    fun insertWallet(wallet: MyWallet) = viewModelScope.launch {
        repository.insert(wallet)
    }

    fun delete(wallet: MyWallet) = viewModelScope.launch {
        repository.delete(wallet)
    }

    fun update(wallet: MyWallet) = viewModelScope.launch {
        repository.update(wallet)
    }
}