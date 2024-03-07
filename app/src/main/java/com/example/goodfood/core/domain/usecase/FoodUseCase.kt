package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    suspend fun insert(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    suspend fun isFoodListEmpty(): Boolean
    fun getListFood(): Flow<List<Food>>
    suspend fun insertAllFood()
}