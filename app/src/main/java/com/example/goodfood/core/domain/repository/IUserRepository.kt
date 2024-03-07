package com.example.goodfood.core.domain.repository

import com.example.goodfood.domain.model.User

interface IUserRepository {
    fun getUser(email: String, password: String): User

    fun isAlreadyExist(email: String): User?

    suspend fun insert(user: User)

    suspend fun delete(user: User)

    suspend fun update(user: User)
}