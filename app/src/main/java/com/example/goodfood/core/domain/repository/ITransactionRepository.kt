package com.example.goodfood.core.domain.repository

import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ITransactionRepository {
    fun getAllTransaction(): Flow<List<Transaction>>

    fun getSubTotal(): Flow<Double>

    suspend fun insert(transaction: Transaction)

    suspend fun update(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun deleteAll()
}