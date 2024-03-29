package com.example.goodfood.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.core.domain.model.Review
import com.example.goodfood.core.domain.usecase.ReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReviewScreenUIState(
    val review: List<Review> = emptyList()
)

@HiltViewModel
class ReviewViewModel @Inject constructor(private val reviewUseCase: ReviewUseCase) : ViewModel() {
    val allReview: LiveData<List<Review?>> = reviewUseCase.getReviews().asLiveData()

    private val _state = MutableStateFlow(ReviewScreenUIState())
    val state = _state.asStateFlow()

    fun updateReviews(review: List<Review>) {
        _state.value = _state.value.copy(review = review)
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