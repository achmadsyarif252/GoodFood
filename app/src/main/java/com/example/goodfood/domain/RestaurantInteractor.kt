package com.example.goodfood.domain

import com.example.goodfood.domain.dao.RestaurantDao
import com.example.goodfood.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

class RestaurantInteractor(private val restaurantRepository: IRestaurantRepository) :
    RestaurantUseCase {
    override fun getAllRestaurant(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurant()
    }


    override suspend fun isListRestaurantEmpty(): Boolean {
        return restaurantRepository.isListRestaurantEmpty()
    }

    override suspend fun insertAllRestaurant(listRestaurant: List<Restaurant>) {
        restaurantRepository.insertAllRestaurant(listRestaurant)
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