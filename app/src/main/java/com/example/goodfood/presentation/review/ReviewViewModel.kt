package com.example.goodfood.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import com.example.goodfood.core.domain.usecase.ReviewUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReviewScreenUIState(
    val reviewEntities: List<ReviewEntity> = emptyList()
)

class ReviewViewModel(private val reviewUseCase: ReviewUseCase) : ViewModel() {
    val allReviewEntity: LiveData<List<ReviewEntity>>

    private val _state = MutableStateFlow(ReviewScreenUIState())
    val state = _state.asStateFlow()

    init {
        allReviewEntity = reviewUseCase.getReviews().asLiveData()
    }

    fun updateReviews(reviewEntities: List<ReviewEntity>) {
        _state.value = _state.value.copy(reviewEntities = reviewEntities)
    }

    fun insert(reviewEntity: ReviewEntity) = viewModelScope.launch {
        reviewUseCase.insert(reviewEntity)
    }

    fun delete(reviewEntity: ReviewEntity) = viewModelScope.launch {
        _state.update {
            reviewUseCase.delete(reviewEntity)
            it
        }
    }

    fun update(reviewEntity: ReviewEntity) = viewModelScope.launch {
        reviewUseCase.update(reviewEntity)
    }


}