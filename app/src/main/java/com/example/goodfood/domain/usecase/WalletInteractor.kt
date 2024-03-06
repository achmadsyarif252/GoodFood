package com.example.goodfood.domain.usecase

import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.repository.IwalletRepsitory
import kotlinx.coroutines.flow.Flow

class WalletInteractor(private val walletRepository: IwalletRepsitory) : WalletUseCase {
    override fun getAllWallet(): Flow<List<MyWallet>> {
        return walletRepository.getAllWallet()
    }

    override suspend fun insertAllWallet() {
        walletRepository.insertAllWallet()
    }

    override suspend fun isWalletListEmpty(): Boolean {
        return walletRepository.isWalletListEmpty()
    }

    override suspend fun insert(wallet: MyWallet) {
        walletRepository.insert(wallet)
    }

    override suspend fun delete(wallet: MyWallet) {
        walletRepository.delete(wallet)
    }

    override suspend fun update(wallet: MyWallet) {
        walletRepository.update(wallet)
    }
}