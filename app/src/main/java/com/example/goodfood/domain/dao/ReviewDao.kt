package com.example.goodfood.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.domain.model.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM table_review ORDER BY id ASC")
    fun getAllReview(): Flow<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: Review)

    @Update
    suspend fun update(review: Review)

    @Delete
    suspend fun delete(review: Review)

}