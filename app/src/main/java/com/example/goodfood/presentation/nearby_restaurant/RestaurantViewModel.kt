package com.example.goodfood.presentation.nearby_restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.domain.model.Restaurant
import com.example.goodfood.core.domain.usecase.RestaurantUseCase
import kotlinx.coroutines.launch

class RestaurantViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val allRestaurantEntity: LiveData<List<Restaurant>> = restaurantUseCase.getAllRestaurant().asLiveData()

    init {
        insertAllRestaurant()
    }

    private fun insertAllRestaurant() = viewModelScope.launch {
        if (restaurantUseCase.isListRestaurantEmpty()) {
            restaurantUseCase.insertAllRestaurant()
        }
    }

    fun insert(restaurant: Restaurant) = viewModelScope.launch {
        restaurantUseCase.insert(restaurant)
    }

    fun update(restaurant: Restaurant) = viewModelScope.launch {
        restaurantUseCase.update(restaurant)
    }

    fun delete(restaurant: Restaurant) = viewModelScope.launch {
        restaurantUseCase.delete(restaurant)
    }

}