package com.example.goodfood.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.data.FoodRepository
import com.example.goodfood.di.Injection
import com.example.goodfood.domain.FoodUseCase

class FoodViewModelFactory(
    private var foodUseCase: FoodUseCase
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: FoodViewModelFactory? = null

        fun getInstance(): FoodViewModelFactory = instance ?: synchronized(this) {
            instance ?: FoodViewModelFactory(Injection.provideUseCase())
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FoodViewModel::class.java) -> FoodViewModel(foodUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}