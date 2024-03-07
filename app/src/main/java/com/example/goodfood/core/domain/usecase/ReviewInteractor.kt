package com.example.goodfood.core.domain.usecase

import com.example.goodfood.domain.model.Review
import com.example.goodfood.domain.repository.IReviewRepository
import kotlinx.coroutines.flow.Flow

class ReviewInteractor(private val reviewRepository: IReviewRepository) : ReviewUseCase {
    override fun getReviews(): Flow<List<Review>> {
        return reviewRepository.getReviews()
    }

    override suspend fun insert(review: Review) {
        reviewRepository.insert(review)
    }

    override suspend fun update(review: Review) {
        reviewRepository.update(review)
    }

    override suspend fun delete(review: Review) {
        reviewRepository.delete(review)
    }
}