package com.example.goodfood.core.domain.usecase

import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {
    fun getAllRestaurant(): Flow<List<Restaurant>>

    suspend fun isListRestaurantEmpty(): Boolean

    suspend fun insertAllRestaurant(listRestaurant: List<Restaurant>)
    suspend fun insert(restaurant: Restaurant)

    suspend fun delete(restaurant: Restaurant)

    suspend fun update(restaurant: Restaurant)
}