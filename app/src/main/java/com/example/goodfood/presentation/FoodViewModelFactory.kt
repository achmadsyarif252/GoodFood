package com.example.goodfood.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.di.Injection
import com.example.goodfood.domain.usecase.FoodUseCase
import com.example.goodfood.domain.usecase.RestaurantUseCase
import com.example.goodfood.domain.usecase.ReviewUseCase
import com.example.goodfood.domain.usecase.TransactionUseCase
import com.example.goodfood.domain.usecase.UserUseCase

class FoodViewModelFactory(
    private var foodUseCase: FoodUseCase,
    private var restaurantUseCase: RestaurantUseCase,
    private var reviewUseCase: ReviewUseCase,
    private var transactionUseCase: TransactionUseCase,
    private var userUseCase: UserUseCase,
    private var context: Context

) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: FoodViewModelFactory? = null

        fun getInstance(): FoodViewModelFactory = instance ?: synchronized(this) {
            instance ?: FoodViewModelFactory(
                Injection.provideFoodUseCase(),
                Injection.provideRestaurantUseCase(),
                Injection.provideReviewUseCase(),
                Injection.provideTransactionUseCase(),
                Injection.provideUserUseCase(),
                context = Injection.provideContext()
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FoodViewModel::class.java) -> FoodViewModel(foodUseCase) as T
            modelClass.isAssignableFrom(RestaurantViewModel::class.java) -> RestaurantViewModel(
                restaurantUseCase
            ) as T

            modelClass.isAssignableFrom(ReviewViewModel::class.java) -> ReviewViewModel(
                reviewUseCase
            ) as T

            modelClass.isAssignableFrom(TransactionViewModel::class.java) -> TransactionViewModel(
                transactionUseCase
            ) as T

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(
                userUseCase
            ) as T

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(context) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}