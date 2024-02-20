package com.example.goodfood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.ReviewRepository
import com.example.goodfood.domain.db.FoodDatabase
import com.example.goodfood.domain.model.Review
import kotlinx.coroutines.launch

class ReviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReviewRepository
    val allReview: LiveData<List<Review>>

    init {
        val reviewDao = FoodDatabase.getDatabase(application).reviewDao()
        repository = ReviewRepository(reviewDao)
        allReview = repository.getReviews().asLiveData()
    }

    fun insert(review: Review) = viewModelScope.launch {
        repository.insert(review)
    }

    fun delete(review: Review) = viewModelScope.launch {
        repository.delete(review)
    }

    fun update(review: Review) = viewModelScope.launch {
        repository.update(review)
    }
}