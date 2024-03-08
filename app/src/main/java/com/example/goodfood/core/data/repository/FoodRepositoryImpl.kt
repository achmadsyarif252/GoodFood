package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.source.local.IFoodDataSource
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.repository.IFoodRepository
import com.example.goodfood.core.helper.InitialDataSource
import com.example.goodfood.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoodRepositoryImpl(private val foodDataSource: IFoodDataSource) : IFoodRepository {

    override suspend fun insert(food: Food) {
        val foodEntity = DataMapper.mapFoodDomainToEntity(food)
        foodDataSource.insert(foodEntity)
    }

    override suspend fun update(food: Food) {
        val foodEntity = DataMapper.mapFoodDomainToEntity(food)
        foodDataSource.update(foodEntity)
    }

    override suspend fun delete(food: Food) {
        val foodEntity = DataMapper.mapFoodDomainToEntity(food)
        foodDataSource.delete(foodEntity)
    }

    override suspend fun isFoodListEmpty(): Boolean {
        return foodDataSource.isFoodListEmpty()
    }

    override suspend fun insertAllFood() {
        foodDataSource.insertAllFood((InitialDataSource.getFood()))
    }

    override fun getListFood(): Flow<List<Food>> {
        return foodDataSource.getAllFood().map { entities ->
            DataMapper.mapFoodEntitiesToDomain(entities)
        }
    }
}
