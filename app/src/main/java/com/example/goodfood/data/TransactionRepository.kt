package com.example.goodfood.data

import androidx.lifecycle.asLiveData
import com.example.goodfood.domain.dao.TransactionDao
import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TransactionRepository(private val transactionDao: TransactionDao) {
    val allTransaction: Flow<List<Transaction>> = transactionDao.getTransaction()

    fun getSubTotal() = allTransaction.map { transaction ->
        transaction.sumOf { it.total * it.food.price }
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }
}