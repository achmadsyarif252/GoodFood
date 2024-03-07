package com.example.goodfood.core.domain.model
data class Review(
    val id: Int = 0,
    val name: String,
     val photo: Int,
    val rating: Int,
    val review: String,
    val food: Food
)
