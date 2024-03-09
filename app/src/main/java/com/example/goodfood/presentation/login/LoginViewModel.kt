package com.example.goodfood.presentation.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.data.LoginInfo
import com.example.goodfood.core.data.UserPreferences
import com.example.goodfood.core.data.UserPreferences.loginInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val application: Application) : ViewModel() {
    // LiveData yang diobservasi oleh UI
    val loginInfo: LiveData<LoginInfo> = application.loginInfo.asLiveData()
    fun saveLoginInfo(username: String, isLoggedIn: Boolean) {
        viewModelScope.launch {
            UserPreferences.saveUserLogin(application, username, isLoggedIn)
        }
    }
}