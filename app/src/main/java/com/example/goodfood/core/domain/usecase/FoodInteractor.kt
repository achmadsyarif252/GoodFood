package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.repository.IFoodRepository
import kotlinx.coroutines.flow.Flow

class FoodInteractor(private val foodRepository: IFoodRepository) : FoodUseCase {
    override suspend fun insert(food: Food) {
        foodRepository.insert(food)
    }

    override suspend fun update(food: Food) {
        foodRepository.update(food)
    }

    override suspend fun delete(food: Food) {
        foodRepository.delete(food)
    }

    override suspend fun isFoodListEmpty(): Boolean {
        return foodRepository.isFoodListEmpty()
    }

    override fun getListFood(): Flow<List<Food>> {
        return foodRepository.getListFood()
    }

    override suspend fun insertAllFood() {
        foodRepository.insertAllFood()
    }
}