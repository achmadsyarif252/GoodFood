package com.example.goodfood.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.data.source.local.entity.UserEntity
import com.example.goodfood.core.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getUser(email: String, password: String): LiveData<UserEntity> {
        return liveData(Dispatchers.IO) {
            emit(userUseCase.getUser(email, password))
        }
    }

    // Fungsi untuk mengecek apakah user sudah ada
    fun isAlreadyExist(email: String): LiveData<UserEntity?> {
        return liveData(Dispatchers.IO) {
            emit(userUseCase.isAlreadyExist(email))
        }
    }

    fun insert(userEntity: UserEntity) = viewModelScope.launch {
        userUseCase.insert(userEntity)
    }

    fun delete(userEntity: UserEntity) = viewModelScope.launch {
        userUseCase.delete(userEntity)
    }

    fun update(userEntity: UserEntity) = viewModelScope.launch {
        userUseCase.update(userEntity)
    }
}