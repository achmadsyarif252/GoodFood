package com.example.goodfood.core.di

import android.app.Application
import androidx.room.Room
import com.example.goodfood.core.data.repository.FoodRepositoryImpl
import com.example.goodfood.core.data.repository.RestaurantRepositoryImpl
import com.example.goodfood.core.data.repository.ReviewRepositoryImpl
import com.example.goodfood.core.data.repository.TransactionRepositoryImpl
import com.example.goodfood.core.data.repository.UserRepositoryImpl
import com.example.goodfood.core.data.repository.WalletRepositoryImpl
import com.example.goodfood.core.data.source.local.FoodDataSource
import com.example.goodfood.core.data.source.local.IFoodDataSource
import com.example.goodfood.core.data.source.local.IRestaurantDataSource
import com.example.goodfood.core.data.source.local.IReviewDataSource
import com.example.goodfood.core.data.source.local.ITransactionDataSource
import com.example.goodfood.core.data.source.local.IUserDataSource
import com.example.goodfood.core.data.source.local.IWalletDataSource
import com.example.goodfood.core.data.source.local.RestaurantDataSource
import com.example.goodfood.core.data.source.local.ReviewDataSource
import com.example.goodfood.core.data.source.local.TransactionDataSource
import com.example.goodfood.core.data.source.local.UserDataSource
import com.example.goodfood.core.data.source.local.WalletDataSource
import com.example.goodfood.core.data.source.local.room.FoodDao
import com.example.goodfood.core.data.source.local.room.FoodDatabase
import com.example.goodfood.core.data.source.local.room.RestaurantDao
import com.example.goodfood.core.data.source.local.room.ReviewDao
import com.example.goodfood.core.data.source.local.room.TransactionDao
import com.example.goodfood.core.data.source.local.room.UserDao
import com.example.goodfood.core.data.source.local.room.WalletDao
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFoodUseCases(
        foodRepository: IFoodRepository
    ): FoodUseCase = FoodInteractor(
        foodRepository
    )

    @Provides
    @Singleton
    fun provideRestaurantUseCase(restaurantRepository: IRestaurantRepository): RestaurantUseCase =
        RestaurantInteractor(restaurantRepository)

    @Provides
    @Singleton
    fun provideReviewUseCase(reviewRepository: IReviewRepository): ReviewUseCase =
        ReviewInteractor(reviewRepository)

    @Provides
    @Singleton
    fun provideTransactionUseCase(transactionRepository: ITransactionRepository): TransactionUseCase =
        TransactionInteractor(transactionRepository)

    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: IUserRepository): UserUseCase =
        UserInteractor(userRepository)

    @Provides
    @Singleton
    fun provideWalletUseCase(walletRepository: IwalletRepsitory): WalletUseCase =
        WalletInteractor(walletRepository)

    @Provides
    @Singleton
    fun provideFoodRepository(foodDataSource: IFoodDataSource): IFoodRepository =
        FoodRepositoryImpl(foodDataSource)

    @Provides
    @Singleton
    fun provideRestaurantRepository(restaurantDataSource: IRestaurantDataSource): IRestaurantRepository =
        RestaurantRepositoryImpl(restaurantDataSource)

    @Provides
    @Singleton
    fun provideReviewRepository(reviewDataSource: IReviewDataSource): IReviewRepository =
        ReviewRepositoryImpl(reviewDataSource)

    @Provides
    @Singleton
    fun provideTransactionRepository(transactionDataSource: ITransactionDataSource): ITransactionRepository =
        TransactionRepositoryImpl(transactionDataSource)

    @Provides
    @Singleton
    fun provideWalletRepository(walletDataSource: IWalletDataSource): IwalletRepsitory =
        WalletRepositoryImpl(walletDataSource)

    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: IUserDataSource): IUserRepository =
        UserRepositoryImpl(userDataSource)

    @Provides
    @Singleton
    fun provideFoodDataSource(foodDao: FoodDao): IFoodDataSource = FoodDataSource(foodDao)

    @Provides
    @Singleton
    fun provideRestaurantDataSource(restaurantDao: RestaurantDao): IRestaurantDataSource =
        RestaurantDataSource(restaurantDao)

    @Provides
    @Singleton
    fun provideReviewDataSource(reviewDao: ReviewDao): IReviewDataSource =
        ReviewDataSource(reviewDao)

    @Provides
    @Singleton
    fun provideTransactionDataSource(transactionDao: TransactionDao): ITransactionDataSource =
        TransactionDataSource(transactionDao)

    @Provides
    @Singleton
    fun provideWalletDataSource(walletDao: WalletDao): IWalletDataSource =
        WalletDataSource(walletDao)

    @Provides
    @Singleton
    fun provideUserDataSource(userDao: UserDao): IUserDataSource = UserDataSource(userDao)

    @Provides
    @Singleton
    fun provideFoodDatabase(application: Application): FoodDatabase {
        return Room.databaseBuilder(
            application,
            FoodDatabase::class.java,
            name = "good_food_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(foodDatabase: FoodDatabase): FoodDao = foodDatabase.foodDao()

    @Provides
    @Singleton
    fun provideRestaurantDao(foodDatabase: FoodDatabase): RestaurantDao =
        foodDatabase.restaurantDao()

    @Provides
    @Singleton
    fun provideReviewDao(foodDatabase: FoodDatabase): ReviewDao = foodDatabase.reviewDao()

    @Provides
    @Singleton
    fun provideTransactionDao(foodDatabase: FoodDatabase): TransactionDao =
        foodDatabase.transactionDao()

    @Provides
    @Singleton
    fun provideWalletDao(foodDatabase: FoodDatabase): WalletDao = foodDatabase.walletDao()

    @Provides
    @Singleton
    fun provideUserDao(foodDatabase: FoodDatabase): UserDao = foodDatabase.userDao()
}