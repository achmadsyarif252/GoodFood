package com.example.goodfood.presentation.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.data.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val application: Application) : ViewModel() {
    fun logout(success: () -> Unit) {
        viewModelScope.launch {
            UserPreferences.clearLoginInfo(application.applicationContext)
            success()
        }
    }
}