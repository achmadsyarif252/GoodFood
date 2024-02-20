package com.example.goodfood.data

import androidx.lifecycle.LiveData
import com.example.goodfood.domain.dao.RestaurantDao
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    val allRestaurant: Flow<List<Restaurant>> = restaurantDao.getAllRestaurant()

    suspend fun isListRestaurantEmpty(): Boolean {
        return restaurantDao.getCount() == 0
    }

    suspend fun insertAllRestaurant() {
        restaurantDao.insertAllRestaurant(InitialDataSource.getRestaurants())
    }

    suspend fun insert(restaurant: Restaurant) {
        restaurantDao.insert(restaurant)
    }

    suspend fun delete(restaurant: Restaurant) {
        restaurantDao.delete(restaurant)
    }

    suspend fun update(restaurant: Restaurant) {
        restaurantDao.update(restaurant)
    }
}