package com.example.goodfood.data

import com.example.goodfood.domain.dao.TransactionDao
import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {
    val allTransaction: Flow<List<Transaction>> = transactionDao.getTransaction()

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        transactionDao.insert(transaction)
    }
}