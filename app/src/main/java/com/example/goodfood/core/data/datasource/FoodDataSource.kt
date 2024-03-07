package com.example.goodfood.core.data.datasource

import com.example.goodfood.core.domain.dao.FoodDao
import com.example.goodfood.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

class FoodDataSource(private val foodDao: FoodDao) : IFoodDataSource {
    override suspend fun insert(food: Food) {
        foodDao.insert(food)
    }

    override suspend fun update(food: Food) {
        foodDao.update(food)
    }

    override suspend fun delete(food: Food) {
        foodDao.delete(food)
    }

    override suspend fun isFoodListEmpty(): Boolean {
        return foodDao.getCount() == 0
    }

    override fun getAllFood(): Flow<List<Food>> {
        return foodDao.getAllFood()
    }

    override suspend fun insertAllFood(listFood: List<Food>) {
        foodDao.insertAllFoods(listFood)
    }

}