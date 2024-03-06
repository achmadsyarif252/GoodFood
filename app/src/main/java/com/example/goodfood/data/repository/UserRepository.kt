package com.example.goodfood.data.repository

import com.example.goodfood.data.datasource.IUserDataSource
import com.example.goodfood.data.datasource.UserDataSource
import com.example.goodfood.domain.dao.UserDao
import com.example.goodfood.domain.model.User
import com.example.goodfood.domain.repository.IUserRepository

class UserRepository(private val userDataSource: IUserDataSource):IUserRepository {

}