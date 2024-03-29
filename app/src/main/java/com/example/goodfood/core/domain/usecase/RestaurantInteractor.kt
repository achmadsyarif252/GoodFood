package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Restaurant
import com.example.goodfood.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow

class RestaurantInteractor(private val restaurantRepository: IRestaurantRepository) :
    RestaurantUseCase {
    override fun getAllRestaurant(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurant()
    }


    override suspend fun isListRestaurantEmpty(): Boolean {
        return restaurantRepository.isListRestaurantEmpty()
    }

    override suspend fun insertAllRestaurant() {
        restaurantRepository.insertAllRestaurant()
    }

    override suspend fun insert(restaurant: Restaurant) {
        restaurantRepository.insert(restaurant)
    }

    override suspend fun delete(restaurant: Restaurant) {
        restaurantRepository.delete(restaurant)
    }

    override suspend fun update(restaurant: Restaurant) {
        restaurantRepository.update(restaurant)
    }
}