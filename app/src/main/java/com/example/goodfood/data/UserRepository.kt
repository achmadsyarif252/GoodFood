package com.example.goodfood.data

import com.example.goodfood.domain.dao.UserDao
import com.example.goodfood.domain.model.User

class UserRepository(private val userDao: UserDao) {

    fun getUser(email: String, password: String): User {
        return userDao.getUser(email, password)
    }

    fun isAlreadyExist(email: String): User? {
        return userDao.isUsernameAlreadyExist(email)
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }
}