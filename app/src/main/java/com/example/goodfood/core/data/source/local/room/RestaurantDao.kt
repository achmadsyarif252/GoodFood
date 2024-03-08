package com.example.goodfood.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.core.data.source.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM table_restaurant ORDER BY id ASC")
    fun getAllRestaurant(): Flow<List<RestaurantEntity>>

    @Query("SELECT COUNT(*) FROM table_restaurant")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRestaurant(restaurantEntities: List<RestaurantEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantEntity: RestaurantEntity)

    @Update
    suspend fun update(restaurantEntity: RestaurantEntity)

    @Delete
    suspend fun delete(restaurantEntity: RestaurantEntity)
}