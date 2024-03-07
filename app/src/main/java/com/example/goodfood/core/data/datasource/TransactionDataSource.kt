package com.example.goodfood.core.data.datasource

import com.example.goodfood.domain.dao.TransactionDao
import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionDataSource(private val transactionDao: TransactionDao) : ITransactionDataSource {
    override fun getAllTransaction(): Flow<List<Transaction>> {
        return transactionDao.getTransaction()
    }

    override fun getSubTotal(): Flow<Double> {
        val allTransaction = getAllTransaction().map { transaction ->
            transaction.sumOf { it.total * it.food.price }
        }
        return allTransaction
    }

    override suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }

    override suspend fun deleteAll() {
        transactionDao.deleteAll()
    }

}