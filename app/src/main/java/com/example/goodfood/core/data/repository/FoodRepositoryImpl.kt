package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.datasource.IFoodDataSource
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.repository.IFoodRepository
import com.example.goodfood.core.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImpl(private val foodDataSource: IFoodDataSource) : IFoodRepository {

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
