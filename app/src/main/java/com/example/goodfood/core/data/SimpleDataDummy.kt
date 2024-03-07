package com.example.goodfood.core.data

import com.example.goodfood.core.data.source.local.entity.MyWallet
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import com.example.goodfood.core.data.source.local.entity.TransactionEntity
import com.example.goodfood.core.data.source.local.entity.listPaymentMethod


object SimpleDataDummy {
    val transactionEntityList = mutableListOf<TransactionEntity>()
    val listReviewEntity = mutableListOf<ReviewEntity>()

    val listMyWallet = listOf(
        MyWallet(wallet = listPaymentMethod[0], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[1], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[2], totalSaldo = 1000.0),
        MyWallet(wallet = listPaymentMethod[3], totalSaldo = 1000.0)

    )


    fun getSubTotal() = transactionEntityList.sumOf { it.foodEntity.price * it.total }
}