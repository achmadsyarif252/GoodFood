package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.data.source.local.entity.UserEntity
import com.example.goodfood.core.domain.repository.IUserRepository


class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override fun getUser(email: String, password: String): UserEntity {
        return userRepository.getUser(email, password)
    }

    override fun isAlreadyExist(email: String): UserEntity? {
        return userRepository.isAlreadyExist(email)
    }

    override suspend fun insert(userEntity: UserEntity) {
        userRepository.insert(userEntity)
    }

    override suspend fun delete(userEntity: UserEntity) {
        userRepository.delete(userEntity)
    }

    override suspend fun update(userEntity: UserEntity) {
        userRepository.update(userEntity)
    }
}