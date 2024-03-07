package com.example.goodfood.core.data.repository

import com.example.goodfood.core.data.source.local.IUserDataSource
import com.example.goodfood.core.data.source.local.entity.UserEntity
import com.example.goodfood.core.domain.repository.IUserRepository


class UserRepositoryImpl(private val userDataSource: IUserDataSource) : IUserRepository {
    override fun getUser(email: String, password: String): UserEntity {
        return userDataSource.getUser(email, password)
    }

    override fun isAlreadyExist(email: String): UserEntity? {
        return userDataSource.isAlreadyExist(email)
    }

    override suspend fun insert(userEntity: UserEntity) {
        userDataSource.insert(userEntity)
    }

    override suspend fun delete(userEntity: UserEntity) {
        userDataSource.delete(userEntity)
    }

    override suspend fun update(userEntity: UserEntity) {
        userDataSource.update(userEntity)
    }

}