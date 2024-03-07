package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.source.local.IReviewDataSource
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import com.example.goodfood.core.domain.repository.IReviewRepository
import kotlinx.coroutines.flow.Flow

class ReviewRepositoryImpl(private val reviewDataSource: IReviewDataSource) : IReviewRepository {
    override fun getReviews(): Flow<List<ReviewEntity>> {
        return reviewDataSource.getReviews()
    }

    override suspend fun insert(reviewEntity: ReviewEntity) {
        reviewDataSource.insert(reviewEntity)
    }

    override suspend fun update(reviewEntity: ReviewEntity) {
        reviewDataSource.update(reviewEntity)
    }

    override suspend fun delete(reviewEntity: ReviewEntity) {
        reviewDataSource.delete(reviewEntity)
    }

}