package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.source.local.ITransactionDataSource
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.core.domain.repository.ITransactionRepository
import com.example.goodfood.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(private val transactionDataSource: ITransactionDataSource) :
    ITransactionRepository {
    override fun getAllTransaction(): Flow<List<Transaction>> {
        return transactionDataSource.getAllTransaction().map {
            DataMapper.mapTransactionEntitiesToDomain(it)
        }
    }

    override fun getSubTotal(): Flow<Double> {
        return transactionDataSource.getSubTotal()
    }

    override suspend fun insert(transaction: Transaction) {
        transactionDataSource.insert(DataMapper.mapTransactionDomainToEntity(transaction))
    }

    override suspend fun update(transaction: Transaction) {
        transactionDataSource.update(DataMapper.mapTransactionDomainToEntity(transaction))
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDataSource.delete(DataMapper.mapTransactionDomainToEntity(transaction))
    }

    override suspend fun deleteAll() {
        transactionDataSource.deleteAll()
    }

}