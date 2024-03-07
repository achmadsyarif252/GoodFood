package com.example.goodfood.core.data.datasource

import com.example.goodfood.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface IRestaurantDataSource {
    fun getAllRestaurant(): Flow<List<Restaurant>>
    suspend fun insertAllRestaurant(list: List<Restaurant>)

    suspend fun insert(restaurant: Restaurant)
    suspend fun update(restaurant: Restaurant)
    suspend fun delete(restaurant: Restaurant)
    suspend fun isRestaurantEmpty(): Boolean
}