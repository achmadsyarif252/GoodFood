package com.example.goodfood.data.repository

import com.example.goodfood.data.datasource.IRestaurantDataSource
import com.example.goodfood.domain.repository.IRestaurantRepository
import com.example.goodfood.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

class RestaurantRepository(private val restaurantDataSource: IRestaurantDataSource) :
    IRestaurantRepository {
    override fun getAllRestaurant(): Flow<List<Restaurant>> {
        return restaurantDataSource.getAllRestaurant()
    }

    override suspend fun isListRestaurantEmpty(): Boolean {
        return restaurantDataSource.isRestaurantEmpty()
    }

    override suspend fun insertAllRestaurant(listRestaurant: List<Restaurant>) {
        restaurantDataSource.insertAllRestaurant(listRestaurant)
    }

    override suspend fun insert(restaurant: Restaurant) {
        restaurantDataSource.insert(restaurant)
    }

    override suspend fun delete(restaurant: Restaurant) {
        restaurantDataSource.delete(restaurant)
    }

    override suspend fun update(restaurant: Restaurant) {
        restaurantDataSource.update(restaurant)
    }


}