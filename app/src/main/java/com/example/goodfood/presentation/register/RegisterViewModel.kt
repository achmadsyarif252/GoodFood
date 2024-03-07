package com.example.goodfood.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.model.User
import com.example.goodfood.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getUser(email: String, password: String): LiveData<User> {
        return liveData(Dispatchers.IO) {
            emit(userUseCase.getUser(email, password))
        }
    }

    // Fungsi untuk mengecek apakah user sudah ada
    fun isAlreadyExist(email: String): LiveData<User?> {
        return liveData(Dispatchers.IO) {
            emit(userUseCase.isAlreadyExist(email))
        }
    }

    fun insert(user: User) = viewModelScope.launch {
        userUseCase.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        userUseCase.delete(user)
    }

    fun update(user: User) = viewModelScope.launch {
        userUseCase.update(user)
    }
}