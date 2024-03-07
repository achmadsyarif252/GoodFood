package com.example.goodfood.core.data.source.local

import com.example.goodfood.core.data.source.local.room.ReviewDao
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

class ReviewDataSource(private val reviewDao: ReviewDao) : IReviewDataSource {
    override fun getReviews(): Flow<List<ReviewEntity>> {
        return reviewDao.getAllReview()
    }

    override suspend fun insert(reviewEntity: ReviewEntity) {
        reviewDao.insert(reviewEntity)
    }

    override suspend fun update(reviewEntity: ReviewEntity) {
        reviewDao.update(reviewEntity)
    }

    override suspend fun delete(reviewEntity: ReviewEntity) {
        reviewDao.delete(reviewEntity)
    }
}