package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionUseCase {
    fun getAllTransaction(): Flow<List<Transaction>>

    fun getSubTotal(): Flow<Double>

    suspend fun insert(transactionEntity: Transaction)

    suspend fun update(transactionEntity: Transaction)

    suspend fun delete(transactionEntity: Transaction)

    suspend fun deleteAll()
}