package com.example.goodfood.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.presentation.login.LoginViewModel
import com.example.goodfood.presentation.profile.LogoutViewModel
/**
 * ViewModelFactory untuk inject konstruktor ke viewmodel
 * cara pemakaian
 * val factory = FoodViewModelFactory.getInstance()
 * val foodViewModel:FoodViewModel = viewModel(factory=factory)
 */
class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(application = application) as T
        }
        if (modelClass.isAssignableFrom(LogoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogoutViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
