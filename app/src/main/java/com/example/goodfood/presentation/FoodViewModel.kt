package com.example.goodfood.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.FoodRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.Food
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FoodRepository
    val allFood: LiveData<List<Food>>

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        allFood = repository.allFood.asLiveData()
        if(allFood.value?.isEmpty() == true)insertAllFoods()
    }

    private fun insertAllFoods() = viewModelScope.launch {
        repository.insertAllFood()
    }

    fun insert(food: Food) = viewModelScope.launch {
        repository.insert(food)
    }

    fun update(food: Food) = viewModelScope.launch {
        repository.update(food)
    }

    fun delete(food: Food) = viewModelScope.launch {
        repository.delete(food)
    }
}
