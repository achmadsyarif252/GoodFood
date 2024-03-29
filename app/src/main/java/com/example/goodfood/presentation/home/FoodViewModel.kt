package com.example.goodfood.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val foodUseCase: FoodUseCase) : ViewModel() {
    val allFood: LiveData<List<Food>> = foodUseCase.getListFood().asLiveData()

    init {
        insertAllFoods()
    }

    private fun insertAllFoods() = viewModelScope.launch {
        if (foodUseCase.isFoodListEmpty()) { // Contoh pengecekan apakah sudah ada data
            foodUseCase.insertAllFood()
        }
    }

    fun insert(food: Food) = viewModelScope.launch {
        foodUseCase.insert(food)
    }

    fun update(food: Food) = viewModelScope.launch {
        foodUseCase.update(food)
    }

    fun delete(food: Food) = viewModelScope.launch {
        foodUseCase.delete(food)
    }
}
