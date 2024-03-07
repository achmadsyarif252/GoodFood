package com.example.goodfood.core.domain.model


data class MyWallet(
    val id: Int = 0,
    val wallet: PaymentMethod,
    val totalSaldo: Double,
)

data class PaymentMethod(
    val name: String,
     val image: Int
)


