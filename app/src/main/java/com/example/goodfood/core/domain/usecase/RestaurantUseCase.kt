package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {
    fun getAllRestaurant(): Flow<List<Restaurant>>

    suspend fun isListRestaurantEmpty(): Boolean

    suspend fun insertAllRestaurant()
    suspend fun insert(restaurant: Restaurant)

    suspend fun delete(restaurant: Restaurant)

    suspend fun update(restaurant: Restaurant)
}