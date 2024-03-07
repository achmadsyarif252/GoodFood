package com.example.goodfood.core.data.datasource

import com.example.goodfood.core.domain.dao.ReviewDao
import com.example.goodfood.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

class ReviewDataSource(private val reviewDao: ReviewDao) : IReviewDataSource {
    override fun getReviews(): Flow<List<Review>> {
        return reviewDao.getAllReview()
    }

    override suspend fun insert(review: Review) {
        reviewDao.insert(review)
    }

    override suspend fun update(review: Review) {
        reviewDao.update(review)
    }

    override suspend fun delete(review: Review) {
        reviewDao.delete(review)
    }
}