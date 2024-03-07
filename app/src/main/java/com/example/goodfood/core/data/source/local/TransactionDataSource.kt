package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.room.TransactionDao
import com.example.goodfood.core.data.source.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionDataSource(private val transactionDao: TransactionDao) : ITransactionDataSource {
    override fun getAllTransaction(): Flow<List<TransactionEntity>> {
        return transactionDao.getTransaction()
    }

    override fun getSubTotal(): Flow<Double> {
        val allTransaction = getAllTransaction().map { transaction ->
            transaction.sumOf { it.total * it.foodEntity.price }
        }
        return allTransaction
    }

    override suspend fun insert(transactionEntity: TransactionEntity) {
        transactionDao.insert(transactionEntity)
    }

    override suspend fun update(transactionEntity: TransactionEntity) {
        transactionDao.update(transactionEntity)
    }

    override suspend fun delete(transactionEntity: TransactionEntity) {
        transactionDao.delete(transactionEntity)
    }

    override suspend fun deleteAll() {
        transactionDao.deleteAll()
    }

}