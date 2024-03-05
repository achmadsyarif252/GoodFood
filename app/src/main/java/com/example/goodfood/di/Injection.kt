package com.example.goodfood.di

import android.content.Context
import com.example.goodfood.data.FoodDataSource
import com.example.goodfood.data.FoodRepository
import com.example.goodfood.data.IFoodDataSource
import com.example.goodfood.domain.FoodInteractor
import com.example.goodfood.domain.FoodUseCase
import com.example.goodfood.domain.IFoodRepository
import com.example.goodfood.domain.dao.FoodDao
import com.example.goodfood.domain.db.FoodDatabase

object Injection {
    private lateinit var appContext: Context

    fun init(context: Context) {
        this.appContext = context.applicationContext
    }

    fun provideUseCase(): FoodUseCase {
        val foodRepository = provideRepository()
        return FoodInteractor(foodRepository)
    }

    private fun provideRepository(): IFoodRepository {
        val foodDataSource = provideDataSource()
        return FoodRepository(foodDataSource)
    }

    private fun provideDataSource(): IFoodDataSource {
        val foodDao = provideFoodDao()
        return FoodDataSource(foodDao)
    }

    private fun provideFoodDao(): FoodDao {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Injection object must be initialized with context first.")
        }
        return FoodDatabase.getDatabase(appContext).foodDao()
    }
}