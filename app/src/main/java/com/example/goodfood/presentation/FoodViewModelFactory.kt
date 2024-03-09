package com.example.goodfood.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.core.di.Injection
import com.example.goodfood.core.domain.usecase.FoodUseCase
import com.example.goodfood.core.domain.usecase.RestaurantUseCase
import com.example.goodfood.core.domain.usecase.ReviewUseCase
import com.example.goodfood.core.domain.usecase.TransactionUseCase
import com.example.goodfood.core.domain.usecase.UserUseCase
import com.example.goodfood.core.domain.usecase.WalletUseCase
import com.example.goodfood.presentation.cart.TransactionViewModel
import com.example.goodfood.presentation.home.FoodViewModel
import com.example.goodfood.presentation.login.LoginViewModel
import com.example.goodfood.presentation.nearby_restaurant.RestaurantViewModel
import com.example.goodfood.presentation.payment.WalletViewModel
import com.example.goodfood.presentation.register.RegisterViewModel
import com.example.goodfood.presentation.review.ReviewViewModel

/**
 * ViewModelFactory untuk inject konstruktor ke viewmodel
 * cara pemakaian
 * val factory = FoodViewModelFactory.getInstance()
 * val foodViewModel:FoodViewModel = viewModel(factory=factory)
 */
class FoodViewModelFactory(
    private var foodUseCase: FoodUseCase,
    private var restaurantUseCase: RestaurantUseCase,
    private var reviewUseCase: ReviewUseCase,
    private var transactionUseCase: TransactionUseCase,
    private var userUseCase: UserUseCase,
    private var application: Application,
    private var walletUseCase: WalletUseCase,

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
                application = Injection.provideContext(),
                Injection.provideWalletUseCase()
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

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(application = application) as T

            modelClass.isAssignableFrom(WalletViewModel::class.java) -> WalletViewModel(
                walletUseCase
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}