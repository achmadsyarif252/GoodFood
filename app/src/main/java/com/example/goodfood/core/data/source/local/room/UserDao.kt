package com.example.goodfood.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM table_user WHERE email = :email")
    fun isUsernameAlreadyExist(email: String): UserEntity?

    @Query("SELECT * FROM table_user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): UserEntity

    @Update
    suspend fun update(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)
}