package com.example.goodfood.core.data.datasource

import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface ITransactionDataSource {
    fun getAllTransaction(): Flow<List<Transaction>>

    fun getSubTotal(): Flow<Double>

    suspend fun insert(transaction: Transaction)

    suspend fun update(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun deleteAll()
}