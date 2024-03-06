package com.example.goodfood.data

import com.example.goodfood.domain.dao.RestaurantDao
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class RestaurantDataSource(private val restaurantDao: RestaurantDao) : IRestaurantDataSource {
    override fun getAllRestaurant(): Flow<List<Restaurant>> {
        return restaurantDao.getAllRestaurant()
    }

    override suspend fun insertAllRestaurant(list: List<Restaurant>) {
        restaurantDao.insertAllRestaurant(list)
    }

    override suspend fun insert(restaurant: Restaurant) {
        restaurantDao.insert(restaurant)
    }

    override suspend fun update(restaurant: Restaurant) {
        restaurantDao.update(restaurant)
    }

    override suspend fun delete(restaurant: Restaurant) {
        restaurantDao.delete(restaurant)
    }

    override suspend fun isRestaurantEmpty(): Boolean {
        return restaurantDao.getCount() == 0
    }
}