package com.example.goodfood.core.data.datasource

import com.example.goodfood.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodDataSource {
    suspend fun insert(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    suspend fun isFoodListEmpty(): Boolean
    fun getAllFood(): Flow<List<Food>>
    suspend fun insertAllFood(listFood: List<Food>)
}