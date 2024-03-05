package com.example.goodfood.data

import com.example.goodfood.domain.dao.FoodDao
import com.example.goodfood.domain.model.Food
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