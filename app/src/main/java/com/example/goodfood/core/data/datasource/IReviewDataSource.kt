package com.example.goodfood.core.data.datasource

import com.example.goodfood.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface IReviewDataSource {
    fun getReviews(): Flow<List<Review>>

    suspend fun insert(review: Review)

    suspend fun update(review: Review)

    suspend fun delete(review: Review)
}