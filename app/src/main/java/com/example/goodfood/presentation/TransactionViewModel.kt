package com.example.goodfood.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.repository.TransactionRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.Transaction
import com.example.goodfood.domain.usecase.TransactionUseCase
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