package com.example.goodfood.core.di

import android.app.Application
import androidx.room.Room
import com.example.goodfood.core.data.repository.FoodRepositoryImpl
import com.example.goodfood.core.data.source.local.FoodDataSource
import com.example.goodfood.core.data.source.local.IFoodDataSource
import com.example.goodfood.core.data.source.local.room.FoodDao
import com.example.goodfood.core.data.source.local.room.FoodDatabase
import com.example.goodfood.core.domain.repository.IFoodRepository
import com.example.goodfood.core.domain.usecase.FoodInteractor
import com.example.goodfood.core.domain.usecase.FoodUseCase
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
    fun provideFoodRepository(foodDataSource: IFoodDataSource): IFoodRepository =
        FoodRepositoryImpl(foodDataSource)

    @Provides
    @Singleton
    fun provideFoodDataSource(foodDao: FoodDao): IFoodDataSource = FoodDataSource(foodDao)

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
//    @Provides
//    fun provideFoodRepository(foodDataSource: IFoodDataSource): IFoodRepository {
//        return FoodRepositoryImpl(foodDataSource)
//    }
//
//    @Provides
//    fun provideFoodDataSource(foodDao: FoodDao): IFoodDataSource {
//        return FoodDataSource(foodDao)
//    }
//
//    @Provides
//    fun provideFoodDao(appDatabase: FoodDatabase): FoodDao {
//        return appDatabase.foodDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): FoodDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            FoodDatabase::class.java, "good_food_database.db"
//        ).fallbackToDestructiveMigration()
//            .build()
//    }
//}
}