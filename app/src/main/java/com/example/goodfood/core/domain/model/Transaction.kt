package com.example.goodfood.core.domain.model
data class Transaction(
    val id: Int = 0,
    var food: Food,
    var total: Int
)