package com.example.goodfood.core.domain.repository

import com.example.goodfood.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface IReviewRepository {
    fun getReviews(): Flow<List<Review>>

    suspend fun insert(review: Review)

    suspend fun update(review: Review)

    suspend fun delete(review: Review)
}