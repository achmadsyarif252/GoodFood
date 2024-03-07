package com.example.goodfood.core.domain.repository

import com.example.goodfood.core.data.source.local.entity.MyWallet
import kotlinx.coroutines.flow.Flow

interface IwalletRepsitory {
    fun getAllWallet(): Flow<List<MyWallet>>

    suspend fun insertAllWallet()

    suspend fun isWalletListEmpty(): Boolean

    suspend fun insert(wallet: MyWallet)

    suspend fun delete(wallet: MyWallet)

    suspend fun update(wallet: MyWallet)
}