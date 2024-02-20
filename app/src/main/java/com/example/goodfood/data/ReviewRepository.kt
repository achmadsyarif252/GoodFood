package com.example.goodfood.data

import com.example.goodfood.domain.dao.ReviewDao
import com.example.goodfood.domain.model.Review
import kotlinx.coroutines.flow.Flow

class ReviewRepository(private val reviewDao: ReviewDao) {
    fun getReviews(): Flow<List<Review>> = reviewDao.getAllReview()

    suspend fun insert(review: Review) {
        reviewDao.insert(review)
    }

    suspend fun update(review: Review) {
        reviewDao.update(review)
    }

    suspend fun delete(review: Review) {
        reviewDao.delete(review)
    }
}