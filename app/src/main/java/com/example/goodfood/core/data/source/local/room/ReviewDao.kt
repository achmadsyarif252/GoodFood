package com.example.goodfood.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM table_review ORDER BY id ASC")
    fun getAllReview(): Flow<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reviewEntity: ReviewEntity)

    @Update
    suspend fun update(reviewEntity: ReviewEntity)

    @Delete
    suspend fun delete(reviewEntity: ReviewEntity)

}