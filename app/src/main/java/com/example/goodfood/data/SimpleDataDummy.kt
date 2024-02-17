package com.example.goodfood.data

import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.model.Review
import com.example.goodfood.domain.model.Transaction

object SimpleDataDummy {
    val transactionList = mutableListOf<Transaction>()
    val listReview = mutableListOf<Review>()
    val listFavoriteFood = mutableListOf<Food>()
    val listFavoriteResto = mutableListOf<Restaurant>()

    fun getSubTotal() = transactionList.sumOf { it.food.price * it.total }
    fun getFee() = if (getSubTotal() > 0) 1.2 else 0.0
    fun getTotalFee() = getSubTotal() + getFee()
}