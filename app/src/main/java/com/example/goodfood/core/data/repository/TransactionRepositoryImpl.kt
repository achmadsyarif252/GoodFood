package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.datasource.ITransactionDataSource
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.core.domain.repository.ITransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(private val transactionDataSource: ITransactionDataSource) :
    ITransactionRepository {
    override fun getAllTransaction(): Flow<List<Transaction>> {
        return transactionDataSource.getAllTransaction()
    }

    override fun getSubTotal(): Flow<Double> {
        return transactionDataSource.getSubTotal()
    }

    override suspend fun insert(transaction: Transaction) {
        transactionDataSource.insert(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        transactionDataSource.update(transaction)
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDataSource.delete(transaction)
    }

    override suspend fun deleteAll() {
        transactionDataSource.deleteAll()
    }

}