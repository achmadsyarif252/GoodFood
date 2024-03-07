package com.example.goodfood.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun getAllFood(): Flow<List<FoodEntity>>

    @Query("SELECT COUNT(*) FROM food_table")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFoods(foodEntities: List<FoodEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodEntity: FoodEntity)

    @Update
    suspend fun update(foodEntity: FoodEntity)

    @Delete
    suspend fun delete(foodEntity: FoodEntity)
}