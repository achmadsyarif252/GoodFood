package com.example.goodfood.data.datasource

import com.example.goodfood.domain.model.User

interface IUserDataSource {
    fun getUser(email: String, password: String): User

    fun isAlreadyExist(email: String): User?

    suspend fun insert(user: User)

    suspend fun delete(user: User)

    suspend fun update(user: User)
}