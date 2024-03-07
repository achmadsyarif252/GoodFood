package com.example.goodfood.presentation.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.data.LoginInfo
import com.example.goodfood.core.data.UserPreferences
import com.example.goodfood.core.data.UserPreferences.loginInfo
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context):ViewModel() {
    // LiveData yang diobservasi oleh UI
    val loginInfo: LiveData<LoginInfo> = context.loginInfo.asLiveData()
    fun saveLoginInfo(username: String, isLoggedIn: Boolean) {
        viewModelScope.launch {
            UserPreferences.saveUserLogin(context, username, isLoggedIn)
        }
    }
}