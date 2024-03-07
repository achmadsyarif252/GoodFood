package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.source.local.IRestaurantDataSource
import com.example.goodfood.core.data.source.local.entity.Restaurant
import com.example.goodfood.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow

class RestaurantRepositoryImpl(private val restaurantDataSource: IRestaurantDataSource) :
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