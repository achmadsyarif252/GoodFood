package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewUseCase {
    fun getReviews(): Flow<List<Review?>>

    suspend fun insert(review: Review)

    suspend fun update(review: Review)

    suspend fun delete(review: Review)
}