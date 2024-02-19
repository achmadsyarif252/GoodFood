package com.example.goodfood.data

import com.example.goodfood.domain.dao.FoodDao
import com.example.goodfood.domain.model.Food
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val foodDao: FoodDao) {
    val allFood: Flow<List<Food>> = foodDao.getAllFood()

    suspend fun insert(food: Food) {
        foodDao.insert(food)
    }

    suspend fun delete(food: Food) {
        foodDao.delete(food)
    }
}
