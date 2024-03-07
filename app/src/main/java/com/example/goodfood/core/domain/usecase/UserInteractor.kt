package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.User
import com.example.goodfood.core.domain.repository.IUserRepository


class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override fun getUser(email: String, password: String): User {
        return userRepository.getUser(email, password)
    }

    override fun isAlreadyExist(email: String): User? {
        return userRepository.isAlreadyExist(email)
    }

    override suspend fun insert(user: User) {
        userRepository.insert(user)
    }

    override suspend fun delete(user: User) {
        userRepository.delete(user)
    }

    override suspend fun update(user: User) {
        userRepository.update(user)
    }
}