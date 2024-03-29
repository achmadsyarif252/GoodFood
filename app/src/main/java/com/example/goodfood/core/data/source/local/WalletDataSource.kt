package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.room.WalletDao
import com.example.goodfood.core.data.source.local.entity.MyWallet
import com.example.goodfood.core.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class WalletDataSource(private val walletDao: WalletDao) : IWalletDataSource {
    override fun getAllWallet(): Flow<List<MyWallet>> {
        return walletDao.getAllWallet()
    }

    override suspend fun insertAllWallet() {
        walletDao.insertAllPayment(InitialDataSource.getAllWallet())
    }

    override suspend fun isWalletListEmpty(): Boolean {
        return walletDao.getCount() == 0
    }

    override suspend fun insert(wallet: MyWallet) {
        walletDao.insert(wallet)
    }

    override suspend fun delete(wallet: MyWallet) {
        walletDao.delete(wallet)
    }

    override suspend fun update(wallet: MyWallet) {
        walletDao.update(wallet)
    }
}