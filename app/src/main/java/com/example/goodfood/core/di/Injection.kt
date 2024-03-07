package com.example.goodfood.core.di

import android.content.Context
import com.example.goodfood.core.data.datasource.FoodDataSource
import com.example.goodfood.core.data.datasource.IFoodDataSource
import com.example.goodfood.core.data.datasource.IRestaurantDataSource
import com.example.goodfood.core.data.datasource.IReviewDataSource
import com.example.goodfood.core.data.datasource.ITransactionDataSource
import com.example.goodfood.core.data.datasource.IUserDataSource
import com.example.goodfood.core.data.datasource.IWalletDataSource
import com.example.goodfood.core.data.datasource.RestaurantDataSource
import com.example.goodfood.core.data.datasource.ReviewDataSource
import com.example.goodfood.core.data.datasource.TransactionDataSource
import com.example.goodfood.core.data.datasource.UserDataSource
import com.example.goodfood.core.data.datasource.WalletDataSource
import com.example.goodfood.core.data.repository.FoodRepositoryImpl
import com.example.goodfood.core.data.repository.RestaurantRepositoryImpl
import com.example.goodfood.core.data.repository.ReviewRepositoryImpl
import com.example.goodfood.core.data.repository.TransactionRepositoryImpl
import com.example.goodfood.core.data.repository.UserRepositoryImpl
import com.example.goodfood.core.data.repository.WalletRepositoryImpl
import com.example.goodfood.core.domain.dao.FoodDao
import com.example.goodfood.core.domain.dao.RestaurantDao
import com.example.goodfood.core.domain.dao.ReviewDao
import com.example.goodfood.core.domain.dao.TransactionDao
import com.example.goodfood.core.domain.dao.UserDao
import com.example.goodfood.core.domain.dao.WalletDao
import com.example.goodfood.core.domain.db.FoodDatabase
import com.example.goodfood.core.domain.repository.IFoodRepository
import com.example.goodfood.core.domain.repository.IRestaurantRepository
import com.example.goodfood.core.domain.repository.IReviewRepository
import com.example.goodfood.core.domain.repository.ITransactionRepository
import com.example.goodfood.core.domain.repository.IUserRepository
import com.example.goodfood.core.domain.repository.IwalletRepsitory
import com.example.goodfood.core.domain.usecase.FoodInteractor
import com.example.goodfood.core.domain.usecase.FoodUseCase
import com.example.goodfood.core.domain.usecase.RestaurantInteractor
import com.example.goodfood.core.domain.usecase.RestaurantUseCase
import com.example.goodfood.core.domain.usecase.ReviewInteractor
import com.example.goodfood.core.domain.usecase.ReviewUseCase
import com.example.goodfood.core.domain.usecase.TransactionInteractor
import com.example.goodfood.core.domain.usecase.TransactionUseCase
import com.example.goodfood.core.domain.usecase.UserInteractor
import com.example.goodfood.core.domain.usecase.UserUseCase
import com.example.goodfood.core.domain.usecase.WalletInteractor
import com.example.goodfood.core.domain.usecase.WalletUseCase


object Injection {
    private lateinit var appContext: Context

    fun init(context: Context) {
        this.appContext = context.applicationContext
    }

    fun provideContext(): Context {
        return appContext
    }

    fun provideFoodUseCase(): FoodUseCase {
        val foodRepository = provideFoodRepository()
        return FoodInteractor(foodRepository)
    }

    fun provideRestaurantUseCase(): RestaurantUseCase {
        val restaurantRepository = provideRestaurantRepository()
        return RestaurantInteractor(restaurantRepository)
    }

    fun provideReviewUseCase(): ReviewUseCase {
        val reviewRepository = provideReviewRepository()
        return ReviewInteractor(reviewRepository)
    }

    fun provideTransactionUseCase(): TransactionUseCase {
        val transactionRepository = provideTransactionRepository()
        return TransactionInteractor(transactionRepository)
    }

    fun provideUserUseCase(): UserUseCase {
        val userRepository = provideUserRepository()
        return UserInteractor(userRepository)
    }

    fun provideWalletUseCase(): WalletUseCase {
        val walletRepository = provideWalletRepository()
        return WalletInteractor(walletRepository)
    }

    private fun provideFoodRepository(): IFoodRepository {
        val foodDataSource = provideFoodDataSource()
        return FoodRepositoryImpl(foodDataSource)
    }

    private fun provideRestaurantRepository(): IRestaurantRepository {
        val restaurantDataSource = provideRestaurantDataSource()
        return RestaurantRepositoryImpl(restaurantDataSource)
    }

    private fun provideReviewRepository(): IReviewRepository {
        val reviewDataSource = provideReviewDataSouce()
        return ReviewRepositoryImpl(reviewDataSource)
    }

    private fun provideTransactionRepository(): ITransactionRepository {
        val transactionDataSource = provideTransactionDataSource()
        return TransactionRepositoryImpl(transactionDataSource)
    }

    private fun provideUserRepository(): IUserRepository {
        val userDataSource = provideUserDataSource()
        return UserRepositoryImpl(userDataSource)
    }

    private fun provideWalletRepository(): IwalletRepsitory {
        val walletDataSource = provideWalletDataSource()
        return WalletRepositoryImpl(walletDataSource)
    }

    private fun provideFoodDataSource(): IFoodDataSource {
        val foodDao = provideFoodDao()
        return FoodDataSource(foodDao)
    }

    private fun provideRestaurantDataSource(): IRestaurantDataSource {
        val restaurantDao = provideRestaurantDao()
        return RestaurantDataSource(restaurantDao)
    }

    private fun provideReviewDataSouce(): IReviewDataSource {
        val reviewDao = provideReviewDao()
        return ReviewDataSource(reviewDao)
    }

    private fun provideTransactionDataSource(): ITransactionDataSource {
        val transactionDao = provideTransactionDao()
        return TransactionDataSource(transactionDao)
    }

    private fun provideUserDataSource(): IUserDataSource {
        val userDao = provideUserDao()
        return UserDataSource(userDao)
    }

    private fun provideWalletDataSource(): IWalletDataSource {
        val walletDao = provideWalletDao()
        return WalletDataSource(walletDao)
    }

    private fun provideFoodDao(): FoodDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first.")
        }
        return FoodDatabase.getDatabase(appContext).foodDao()
    }

    private fun provideRestaurantDao(): RestaurantDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first.")
        }
        return FoodDatabase.getDatabase(appContext).restaurantDao()
    }

    private fun provideReviewDao(): ReviewDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first.")
        }
        return FoodDatabase.getDatabase(appContext).reviewDao()
    }

    private fun provideTransactionDao(): TransactionDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first")
        } else {
            return FoodDatabase.getDatabase(appContext).transactionDao()
        }
    }

    private fun provideUserDao(): UserDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first")
        } else {
            return FoodDatabase.getDatabase(appContext).userDao()
        }
    }

    private fun provideWalletDao(): WalletDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first")
        } else {
            return FoodDatabase.getDatabase(appContext).walletDao()
        }
    }

}