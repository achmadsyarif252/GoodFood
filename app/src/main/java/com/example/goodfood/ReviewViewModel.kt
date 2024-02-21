package com.example.goodfood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.ReviewRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.Review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReviewScreenUIState(
    val reviews: List<Review> = emptyList()
)

class ReviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReviewRepository
    val allReview: LiveData<List<Review>>

    private val _state = MutableStateFlow(ReviewScreenUIState())
    val state = _state.asStateFlow()

    init {
        val reviewDao = FoodDatabase.getDatabase(application).reviewDao()
        repository = ReviewRepository(reviewDao)
        allReview = repository.getReviews().asLiveData()
    }

    fun updateReviews(reviews: List<Review>) {
        _state.value = _state.value.copy(reviews = reviews)
    }

    fun insert(review: Review) = viewModelScope.launch {
        repository.insert(review)
    }

    fun delete(review: Review) = viewModelScope.launch {
        _state.update {
            repository.delete(review)
            it
        }
    }

    fun update(review: Review) = viewModelScope.launch {
        repository.update(review)
    }


}