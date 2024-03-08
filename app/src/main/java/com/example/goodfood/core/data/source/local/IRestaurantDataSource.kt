package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

interface IRestaurantDataSource {
    fun getAllRestaurant(): Flow<List<RestaurantEntity>>
    suspend fun insertAllRestaurant(list: List<RestaurantEntity>)

    suspend fun insert(restaurantEntity: RestaurantEntity)
    suspend fun update(restaurantEntity: RestaurantEntity)
    suspend fun delete(restaurantEntity: RestaurantEntity)
    suspend fun isRestaurantEmpty(): Boolean
}