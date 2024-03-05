package com.example.goodfood.domain

import com.example.goodfood.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {
    suspend fun insert(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    suspend fun isFoodListEmpty(): Boolean
    fun getListFood(): Flow<List<Food>>
    suspend fun insertAllFood()
}