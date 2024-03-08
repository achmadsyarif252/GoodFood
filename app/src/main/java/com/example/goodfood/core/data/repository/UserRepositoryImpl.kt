package com.example.goodfood.core.data.repository

import com.example.goodfood.core.data.source.local.IUserDataSource
import com.example.goodfood.core.data.source.local.entity.UserEntity
import com.example.goodfood.core.domain.model.User
import com.example.goodfood.core.domain.repository.IUserRepository
import com.example.goodfood.core.utils.DataMapper


class UserRepositoryImpl(private val userDataSource: IUserDataSource) : IUserRepository {
    override fun getUser(email: String, password: String): User {
        return DataMapper.mapUserEntityToDomain(
            userDataSource.getUser(email, password) ?: UserEntity()
        )
    }

    override fun isAlreadyExist(email: String): User {
        return DataMapper.mapUserEntityToDomain(
            userDataSource.isAlreadyExist(email) ?: UserEntity()
        )
    }

    override suspend fun insert(user: User) {
        userDataSource.insert(DataMapper.mapUserDomainToEntity(user))
    }

    override suspend fun delete(user: User) {
        userDataSource.delete(DataMapper.mapUserDomainToEntity(user))
    }

    override suspend fun update(user: User) {
        userDataSource.update(DataMapper.mapUserDomainToEntity(user))
    }

}