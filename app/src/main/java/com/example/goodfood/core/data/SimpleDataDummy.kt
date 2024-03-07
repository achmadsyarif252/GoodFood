package com.example.goodfood.core.data

import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.model.Review
import com.example.goodfood.domain.model.Transaction
import com.example.goodfood.domain.model.listPaymentMethod

object SimpleDataDummy {
    val transactionList = mutableListOf<Transaction>()
    val listReview = mutableListOf<Review>()

    val listMyWallet = listOf(
        MyWallet(wallet = listPaymentMethod[0], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[1], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[2], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[3], totalSaldo = 1000.0)

    )


    fun getSubTotal() = transactionList.sumOf { it.food.price * it.total }
}