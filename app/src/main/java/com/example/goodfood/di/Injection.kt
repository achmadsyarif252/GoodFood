package com.example.goodfood.di

import android.content.Context
import com.example.goodfood.data.datasource.FoodDataSource
import com.example.goodfood.data.repository.FoodRepository
import com.example.goodfood.data.datasource.IFoodDataSource
import com.example.goodfood.data.datasource.IRestaurantDataSource
import com.example.goodfood.data.datasource.RestaurantDataSource
import com.example.goodfood.data.repository.RestaurantRepository
import com.example.goodfood.domain.usecase.FoodInteractor
import com.example.goodfood.domain.usecase.FoodUseCase
import com.example.goodfood.domain.repository.IFoodRepository
import com.example.goodfood.domain.repository.IRestaurantRepository
import com.example.goodfood.domain.usecase.RestaurantInteractor
import com.example.goodfood.domain.usecase.RestaurantUseCase
import com.example.goodfood.domain.dao.FoodDao
import com.example.goodfood.domain.dao.RestaurantDao
import com.example.goodfood.domain.db.FoodDatabase

object Injection {
    private lateinit var appContext: Context

    fun init(context: Context) {
        this.appContext = context.applicationContext
    }

    fun provideFoodUseCase(): FoodUseCase {
        val foodRepository = provideFoodRepository()
        return FoodInteractor(foodRepository)
    }

    fun provideRestaurantUseCase(): RestaurantUseCase {
        val restaurantRepository = provideRestaurantRepository()
        return RestaurantInteractor(restaurantRepository)
    }

    private fun provideFoodRepository(): IFoodRepository {
        val foodDataSource = provideFoodDataSource()
        return FoodRepository(foodDataSource)
    }

    private fun provideRestaurantRepository(): IRestaurantRepository {
        val restaurantDataSource = provideRestaurantDataSource()
        return RestaurantRepository(restaurantDataSource)
    }

    private fun provideFoodDataSource(): IFoodDataSource {
        val foodDao = provideFoodDao()
        return FoodDataSource(foodDao)
    }

    private fun provideRestaurantDataSource(): IRestaurantDataSource {
        val restaurantDao = provideRestaurantDao()
        return RestaurantDataSource(restaurantDao)
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
}