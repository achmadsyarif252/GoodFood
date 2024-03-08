package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.User


interface UserUseCase {
    fun getUser(email: String, password: String): User

    fun isAlreadyExist(email: String): User?

    suspend fun insert(userEntity: User)

    suspend fun delete(userEntity: User)

    suspend fun update(userEntity: User)
}