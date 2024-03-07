package com.example.goodfood.core.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM table_user WHERE email = :email")
    fun isUsernameAlreadyExist(email: String): User?

    @Query("SELECT * FROM table_user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): User

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}