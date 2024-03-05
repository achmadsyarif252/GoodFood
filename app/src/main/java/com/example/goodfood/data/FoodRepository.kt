package com.example.goodfood.data

import com.example.goodfood.domain.IFoodRepository
import com.example.goodfood.domain.dao.FoodDao
import com.example.goodfood.domain.model.Food
import com.example.goodfood.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val foodDataSource: IFoodDataSource) : IFoodRepository {
    val allFood: Flow<List<Food>> = getListFood()

    override suspend fun insert(food: Food) {
        foodDataSource.insert(food)
    }

    override suspend fun update(food: Food) {
        foodDataSource.update(food)
    }

    override suspend fun delete(food: Food) {
        foodDataSource.delete(food)
    }

    override suspend fun isFoodListEmpty(): Boolean {
        return foodDataSource.isFoodListEmpty()
    }

    override suspend fun insertAllFood() {
        foodDataSource.insertAllFood((InitialDataSource.getFood()))
    }

    override fun getListFood(): Flow<List<Food>> {
        return foodDataSource.getAllFood()
    }
}
