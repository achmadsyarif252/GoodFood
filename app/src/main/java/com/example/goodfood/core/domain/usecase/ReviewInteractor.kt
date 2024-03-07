package com.example.goodfood.core.domain.usecase

import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import com.example.goodfood.core.domain.repository.IReviewRepository
import kotlinx.coroutines.flow.Flow

class ReviewInteractor(private val reviewRepository: IReviewRepository) : ReviewUseCase {
    override fun getReviews(): Flow<List<ReviewEntity>> {
        return reviewRepository.getReviews()
    }

    override suspend fun insert(reviewEntity: ReviewEntity) {
        reviewRepository.insert(reviewEntity)
    }

    override suspend fun update(reviewEntity: ReviewEntity) {
        reviewRepository.update(reviewEntity)
    }

    override suspend fun delete(reviewEntity: ReviewEntity) {
        reviewRepository.delete(reviewEntity)
    }
}