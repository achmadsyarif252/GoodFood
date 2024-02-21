package com.example.goodfood.data

import com.example.goodfood.domain.dao.WalletDao
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class WalletRepository(private val myWalletDao: WalletDao) {
    val allWallet: Flow<List<MyWallet>> = myWalletDao.getAllWallet()

    suspend fun insertAllWallet() {
        myWalletDao.insertAllPayment(InitialDataSource.getAllWallet())
    }

    suspend fun isWalletListEmpty(): Boolean {
        return myWalletDao.getCount() == 0
    }

    suspend fun insert(wallet: MyWallet) {
        myWalletDao.insert(wallet)
    }

    suspend fun delete(wallet: MyWallet) {
        myWalletDao.delete(wallet)
    }

    suspend fun update(wallet: MyWallet) {
        myWalletDao.update(wallet)
    }
}