package com.example.goodfood.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodfood.presentation.LoginViewModel
import com.example.goodfood.presentation.LogoutViewModel

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(context) as T
        }
        if (modelClass.isAssignableFrom(LogoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogoutViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
