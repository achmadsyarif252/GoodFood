package com.example.goodfood.presentation.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.UserPreferences
import kotlinx.coroutines.launch

class LogoutViewModel(private val context: Context) : ViewModel() {
    fun logout(success: () -> Unit) {
        viewModelScope.launch {
            UserPreferences.clearLoginInfo(context)
            success()
        }
    }
}