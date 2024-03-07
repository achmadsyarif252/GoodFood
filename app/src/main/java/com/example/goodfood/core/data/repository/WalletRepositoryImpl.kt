package com.example.goodfood.core.data.repository

import com.example.goodfood.core.data.datasource.IWalletDataSource
import com.example.goodfood.core.domain.model.MyWallet
import com.example.goodfood.core.domain.repository.IwalletRepsitory
import kotlinx.coroutines.flow.Flow

class WalletRepositoryImpl(private val walletDataSource: IWalletDataSource) : IwalletRepsitory {
    override fun getAllWallet(): Flow<List<MyWallet>> {
        return walletDataSource.getAllWallet()
    }

    override suspend fun insertAllWallet() {
        walletDataSource.insertAllWallet()
    }

    override suspend fun isWalletListEmpty(): Boolean {
        return walletDataSource.isWalletListEmpty()
    }

    override suspend fun insert(wallet: MyWallet) {
        walletDataSource.insert(wallet)
    }

    override suspend fun delete(wallet: MyWallet) {
        walletDataSource.delete(wallet)
    }

    override suspend fun update(wallet: MyWallet) {
        walletDataSource.update(wallet)
    }

}