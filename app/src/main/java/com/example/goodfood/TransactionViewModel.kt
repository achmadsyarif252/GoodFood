package com.example.goodfood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.TransactionRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TransactionRepository
    val allTransaction: LiveData<List<Transaction>>

    init {
        val transactionDao = FoodDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        allTransaction = repository.allTransaction.asLiveData()
    }

    fun getSubTotal() = repository.getSubTotal()

    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }


    fun update(transaction: Transaction) = viewModelScope.launch {
        repository.update(transaction)
    }

    fun delete(transaction: Transaction) = viewModelScope.launch {
        repository.delete(transaction)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}