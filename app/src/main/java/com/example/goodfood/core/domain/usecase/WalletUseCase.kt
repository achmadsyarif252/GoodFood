package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.MyWallet
import kotlinx.coroutines.flow.Flow

interface WalletUseCase {
    fun getAllWallet(): Flow<List<MyWallet>>

    suspend fun insertAllWallet()

    suspend fun isWalletListEmpty(): Boolean

    suspend fun insert(wallet: MyWallet)

    suspend fun delete(wallet: MyWallet)
    suspend fun update(wallet: MyWallet)
}