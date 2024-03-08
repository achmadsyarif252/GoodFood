package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.source.local.IReviewDataSource
import com.example.goodfood.core.domain.model.Review
import com.example.goodfood.core.domain.repository.IReviewRepository
import com.example.goodfood.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReviewRepositoryImpl(private val reviewDataSource: IReviewDataSource) : IReviewRepository {
    override fun getReviews(): Flow<List<Review?>> {
        return reviewDataSource.getReviews().map {
            DataMapper.mapReviewEntitiesToDomain(it)
        }
    }

    override suspend fun insert(review: Review) {
        reviewDataSource.insert(DataMapper.mapReviewDomainToEntity(review))
    }

    override suspend fun update(review: Review) {
        reviewDataSource.update(DataMapper.mapReviewDomainToEntity(review))
    }

    override suspend fun delete(review: Review) {
        reviewDataSource.delete(DataMapper.mapReviewDomainToEntity(review))
    }

}