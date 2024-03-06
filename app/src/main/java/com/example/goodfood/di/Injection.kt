package com.example.goodfood.di

import android.content.Context
import com.example.goodfood.data.FoodDataSource
import com.example.goodfood.data.FoodRepository
import com.example.goodfood.data.IFoodDataSource
import com.example.goodfood.data.IRestaurantDataSource
import com.example.goodfood.data.RestaurantDataSource
import com.example.goodfood.data.RestaurantRepository
import com.example.goodfood.domain.FoodInteractor
import com.example.goodfood.domain.FoodUseCase
import com.example.goodfood.domain.IFoodRepository
import com.example.goodfood.domain.IRestaurantRepository
import com.example.goodfood.domain.RestaurantInteractor
import com.example.goodfood.domain.RestaurantUseCase
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