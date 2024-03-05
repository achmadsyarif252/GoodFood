package com.example.goodfood.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.RestaurantRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.Restaurant
import kotlinx.coroutines.launch

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RestaurantRepository
    val allRestaurant: LiveData<List<Restaurant>>

    init {
        val restaurantDao = FoodDatabase.getDatabase(application).restaurantDao()
        repository = RestaurantRepository(restaurantDao)
        allRestaurant = repository.allRestaurant.asLiveData()
        insertAllRestaurant()
    }

    private fun insertAllRestaurant() = viewModelScope.launch {
        if (repository.isListRestaurantEmpty()) {
            repository.insertAllRestaurant()
        }
    }

    fun insert(restaurant: Restaurant) = viewModelScope.launch {
        repository.insert(restaurant)
    }

    fun update(restaurant: Restaurant) = viewModelScope.launch {
        repository.update(restaurant)
    }

    fun delete(restaurant: Restaurant) = viewModelScope.launch {
        repository.delete(restaurant)
    }

}