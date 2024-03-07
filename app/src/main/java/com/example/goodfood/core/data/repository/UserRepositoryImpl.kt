package com.example.goodfood.core.data.repository

import com.example.goodfood.data.datasource.IUserDataSource
import com.example.goodfood.domain.model.User
import com.example.goodfood.domain.repository.IUserRepository

class UserRepositoryImpl(private val userDataSource: IUserDataSource) : IUserRepository {
    override fun getUser(email: String, password: String): User {
        return userDataSource.getUser(email, password)
    }

    override fun isAlreadyExist(email: String): User? {
        return userDataSource.isAlreadyExist(email)
    }

    override suspend fun insert(user: User) {
        userDataSource.insert(user)
    }

    override suspend fun delete(user: User) {
        userDataSource.delete(user)
    }

    override suspend fun update(user: User) {
        userDataSource.update(user)
    }

}