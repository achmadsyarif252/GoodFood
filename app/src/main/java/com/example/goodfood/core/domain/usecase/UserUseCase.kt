package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.data.source.local.entity.UserEntity


interface UserUseCase {
    fun getUser(email: String, password: String): UserEntity

    fun isAlreadyExist(email: String): UserEntity?

    suspend fun insert(userEntity: UserEntity)

    suspend fun delete(userEntity: UserEntity)

    suspend fun update(userEntity: UserEntity)
}