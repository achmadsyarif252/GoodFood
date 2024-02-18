package com.example.goodfood.data

import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.model.Review
import com.example.goodfood.domain.model.Transaction
import com.example.goodfood.domain.model.listPaymentMethod

object SimpleDataDummy {
    val transactionList = mutableListOf<Transaction>()
    val listReview = mutableListOf<Review>()
    val listFavoriteFood = mutableListOf<Food>()
    val listFavoriteResto = mutableListOf<Restaurant>()

    val listMyWallet = listOf(
        MyWallet(wallet = listPaymentMethod[0], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[1], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[2], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[3], totalSaldo = 1000.0)

    )


    fun getSubTotal() = transactionList.sumOf { it.food.price * it.total }
    fun getFee() = if (getSubTotal() > 0) 1.2 else 0.0
    fun getTotalFee() = getSubTotal() + getFee()
}