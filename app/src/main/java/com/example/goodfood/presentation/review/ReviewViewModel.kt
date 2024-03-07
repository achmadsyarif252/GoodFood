package com.example.goodfood.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.domain.model.Review
import com.example.goodfood.core.domain.usecase.ReviewUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReviewScreenUIState(
    val reviews: List<Review> = emptyList()
)

class ReviewViewModel(private val reviewUseCase: ReviewUseCase) : ViewModel() {
    val allReview: LiveData<List<Review>>

    private val _state = MutableStateFlow(ReviewScreenUIState())
    val state = _state.asStateFlow()

    init {
        allReview = reviewUseCase.getReviews().asLiveData()
    }

    fun updateReviews(reviews: List<Review>) {
        _state.value = _state.value.copy(reviews = reviews)
    }

    fun insert(review: Review) = viewModelScope.launch {
        reviewUseCase.insert(review)
    }

    fun delete(review: Review) = viewModelScope.launch {
        _state.update {
            reviewUseCase.delete(review)
            it
        }
    }

    fun update(review: Review) = viewModelScope.launch {
        reviewUseCase.update(review)
    }


}