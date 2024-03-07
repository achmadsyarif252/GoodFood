package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

interface IFoodDataSource {
    suspend fun insert(foodEntity: FoodEntity)
    suspend fun update(foodEntity: FoodEntity)
    suspend fun delete(foodEntity: FoodEntity)
    suspend fun isFoodListEmpty(): Boolean
    fun getAllFood(): Flow<List<FoodEntity>>
    suspend fun insertAllFood(listFoodEntity: List<FoodEntity>)
}