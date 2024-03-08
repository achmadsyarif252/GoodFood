package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.entity.RestaurantEntity
import com.example.goodfood.core.data.source.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow

class RestaurantDataSource(private val restaurantDao: RestaurantDao) : IRestaurantDataSource {
    override fun getAllRestaurant(): Flow<List<RestaurantEntity>> {
        return restaurantDao.getAllRestaurant()
    }

    override suspend fun insertAllRestaurant(list: List<RestaurantEntity>) {
        restaurantDao.insertAllRestaurant(list)
    }

    override suspend fun insert(restaurantEntity: RestaurantEntity) {
        restaurantDao.insert(restaurantEntity)
    }

    override suspend fun update(restaurantEntity: RestaurantEntity) {
        restaurantDao.update(restaurantEntity)
    }

    override suspend fun delete(restaurantEntity: RestaurantEntity) {
        restaurantDao.delete(restaurantEntity)
    }

    override suspend fun isRestaurantEmpty(): Boolean {
        return restaurantDao.getCount() == 0
    }
}