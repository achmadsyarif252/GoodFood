package com.example.goodfood.core.domain.repository

import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

interface IReviewRepository {
    fun getReviews(): Flow<List<ReviewEntity>>

    suspend fun insert(reviewEntity: ReviewEntity)

    suspend fun update(reviewEntity: ReviewEntity)

    suspend fun delete(reviewEntity: ReviewEntity)
}