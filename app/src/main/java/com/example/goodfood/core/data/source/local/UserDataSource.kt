package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.room.UserDao
import com.example.goodfood.core.data.source.local.entity.UserEntity


class UserDataSource(private val userDao: UserDao) : IUserDataSource {
    override fun getUser(email: String, password: String): UserEntity {
        return userDao.getUser(email, password)
    }

    override fun isAlreadyExist(email: String): UserEntity? {
        return userDao.isUsernameAlreadyExist(email)
    }

    override suspend fun insert(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    override suspend fun delete(userEntity: UserEntity) {
        userDao.delete(userEntity)
    }

    override suspend fun update(userEntity: UserEntity) {
        userDao.update(userEntity)
    }
}