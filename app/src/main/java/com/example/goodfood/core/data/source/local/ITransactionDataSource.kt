package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface ITransactionDataSource {
    fun getAllTransaction(): Flow<List<TransactionEntity>>

    fun getSubTotal(): Flow<Double>

    suspend fun insert(transactionEntity: TransactionEntity)

    suspend fun update(transactionEntity: TransactionEntity)

    suspend fun delete(transactionEntity: TransactionEntity)

    suspend fun deleteAll()
}