package com.example.goodfood.core.data.datasource

import com.example.goodfood.core.domain.model.MyWallet
import kotlinx.coroutines.flow.Flow

interface IWalletDataSource {
    fun getAllWallet(): Flow<List<MyWallet>>

    suspend fun insertAllWallet()

    suspend fun isWalletListEmpty(): Boolean

    suspend fun insert(wallet: MyWallet)

    suspend fun delete(wallet: MyWallet)

    suspend fun update(wallet: MyWallet)
}