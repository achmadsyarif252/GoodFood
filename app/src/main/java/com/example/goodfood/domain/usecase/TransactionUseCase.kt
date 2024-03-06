package com.example.goodfood.domain.usecase

import com.example.goodfood.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TransactionUseCase {
    fun getAllTransaction(): Flow<List<Transaction>>

    fun getSubTotal(): Flow<Double>

    suspend fun insert(transaction: Transaction)

    suspend fun update(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun deleteAll()
}