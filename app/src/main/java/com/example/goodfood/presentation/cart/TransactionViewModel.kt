package com.example.goodfood.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.core.domain.usecase.TransactionUseCase

import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionUseCase: TransactionUseCase) : ViewModel() {
    val allTransaction: LiveData<List<Transaction>> = transactionUseCase.getAllTransaction().asLiveData()

    fun getSubTotal() = transactionUseCase.getSubTotal()

    fun insert(transaction: Transaction) = viewModelScope.launch {
        transactionUseCase.insert(transaction)
    }


    fun update(transaction: Transaction) = viewModelScope.launch {
        transactionUseCase.update(transaction)
    }

    fun delete(transaction: Transaction) = viewModelScope.launch {
        transactionUseCase.delete(transaction)
    }

    fun deleteAll() = viewModelScope.launch {
        transactionUseCase.deleteAll()
    }

}