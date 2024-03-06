package com.example.goodfood.domain.repository

import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

interface IwalletRepsitory {
    fun getAllWallet(): Flow<List<MyWallet>>

    suspend fun insertAllWallet()

    suspend fun isWalletListEmpty(): Boolean

    suspend fun insert(wallet: MyWallet)

    suspend fun delete(wallet: MyWallet)

    suspend fun update(wallet: MyWallet)
}