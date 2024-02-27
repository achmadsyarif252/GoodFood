package com.example.goodfood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.UserRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = FoodDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUser(email: String, password: String): LiveData<User> {
        return liveData(Dispatchers.IO) {
            emit(repository.getUser(email, password))
        }
    }

    // Fungsi untuk mengecek apakah user sudah ada
    fun isAlreadyExist(email: String): LiveData<User?> {
        return liveData(Dispatchers.IO) {
            emit(repository.isAlreadyExist(email))
        }
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }
}