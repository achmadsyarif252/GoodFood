package com.example.goodfood.core.data.repository

import com.example.goodfood.data.datasource.IReviewDataSource
import com.example.goodfood.domain.model.Review
import com.example.goodfood.domain.repository.IReviewRepository
import kotlinx.coroutines.flow.Flow

class ReviewRepositoryImpl(private val reviewDataSource: IReviewDataSource) : IReviewRepository {
    override fun getReviews(): Flow<List<Review>> {
        return reviewDataSource.getReviews()
    }

    override suspend fun insert(review: Review) {
        reviewDataSource.insert(review)
    }

    override suspend fun update(review: Review) {
        reviewDataSource.update(review)
    }

    override suspend fun delete(review: Review) {
        reviewDataSource.delete(review)
    }

}