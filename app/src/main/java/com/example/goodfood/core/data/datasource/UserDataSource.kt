package com.example.goodfood.core.data.datasource

import com.example.goodfood.domain.dao.UserDao
import com.example.goodfood.domain.model.User

class UserDataSource(private val userDao: UserDao) : IUserDataSource {
    override fun getUser(email: String, password: String): User {
        return userDao.getUser(email, password)
    }

    override fun isAlreadyExist(email: String): User? {
        return userDao.isUsernameAlreadyExist(email)
    }

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }
}