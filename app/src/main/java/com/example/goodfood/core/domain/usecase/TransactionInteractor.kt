package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.core.domain.repository.ITransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionInteractor(private val transactioRepository: ITransactionRepository) :
    TransactionUseCase {
    override fun getAllTransaction(): Flow<List<Transaction>> {
        return transactioRepository.getAllTransaction()
    }

    override fun getSubTotal(): Flow<Double> {
        return transactioRepository.getSubTotal()
    }

    override suspend fun insert(transaction: Transaction) {
        transactioRepository.insert(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        transactioRepository.update(transaction)
    }

    override suspend fun delete(transaction: Transaction) {
        transactioRepository.delete(transaction)
    }

    override suspend fun deleteAll() {
        transactioRepository.deleteAll()
    }
}