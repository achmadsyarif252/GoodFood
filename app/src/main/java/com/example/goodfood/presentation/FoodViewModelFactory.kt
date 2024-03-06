package com.example.goodfood.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.di.Injection
import com.example.goodfood.domain.usecase.FoodUseCase
import com.example.goodfood.domain.usecase.RestaurantUseCase

class FoodViewModelFactory(
    private var foodUseCase: FoodUseCase,
    private var restaurantUseCase: RestaurantUseCase
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: FoodViewModelFactory? = null

        fun getInstance(): FoodViewModelFactory = instance ?: synchronized(this) {
            instance ?: FoodViewModelFactory(
                Injection.provideFoodUseCase(),
                Injection.provideRestaurantUseCase()
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FoodViewModel::class.java) -> FoodViewModel(foodUseCase) as T
            modelClass.isAssignableFrom(RestaurantViewModel::class.java) -> RestaurantViewModel(
                restaurantUseCase
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}